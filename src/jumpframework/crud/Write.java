package jumpframework.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.WordUtils;


public class Write {
	
	private Map<String, String> mapNameProperties;
	private String tableName;
	/*
	 * all field in table : array 
	 */
	private String[][] fields;
	
	/**
	 * convert SQL data type to JAVA Type variable
	 */
	protected HashMap<String, String> getTypeVariable(){
		HashMap<String, String> typeVariable = new HashMap<String, String>();		
		typeVariable.put("BIT","byte");
		typeVariable.put("TINYINT","byte");
		typeVariable.put("SMALLINT","short");
		typeVariable.put("INTEGER","int");
		typeVariable.put("BIGINT","long");
		typeVariable.put("FLOAT","float");
		typeVariable.put("REAL","double");
		typeVariable.put("DOUBLE","double");
		typeVariable.put("NUMERIC","double");
		typeVariable.put("DECIMAL","double");
		typeVariable.put("CHAR","String");
		typeVariable.put("VARCHAR","String");
		typeVariable.put("LONGVARCHAR","String");
		typeVariable.put("DATE","String");
		typeVariable.put("TIME","String");
		typeVariable.put("TIMESTAMP","String");
		typeVariable.put("BINARY","String");
		typeVariable.put("VARBINARY","String");
		typeVariable.put("LONGVARBINARY","String");
		typeVariable.put("BOOLEAN","String");
		return typeVariable;
	}
	
	/**
	 * convert SQL data type to Html type
	 */
	protected HashMap<String, String> getTypeHtml(){
		HashMap<String, String> typeVariable = new HashMap<String, String>();		
		typeVariable.put("BIT","number");
		typeVariable.put("TINYINT","number");
		typeVariable.put("SMALLINT","number");
		typeVariable.put("INTEGER","number");
		typeVariable.put("BIGINT","number");
		typeVariable.put("FLOAT","number");
		typeVariable.put("REAL","number");
		typeVariable.put("DOUBLE","number");
		typeVariable.put("NUMERIC","number");
		typeVariable.put("DECIMAL","number");
		typeVariable.put("CHAR","text");
		typeVariable.put("VARCHAR","text");
		typeVariable.put("LONGVARCHAR","textarea");
		typeVariable.put("DATE","date");
		typeVariable.put("TIME","time");
		typeVariable.put("TIMESTAMP","datetime");
		typeVariable.put("BINARY","text");
		typeVariable.put("VARBINARY","text");
		typeVariable.put("LONGVARBINARY","text");
		typeVariable.put("BOOLEAN","text");
		return typeVariable;
	}
	
	/**
	 * write \t
	 * 
	 * @param n = number of \t
	 * @return string \t n times
	 */
	protected String wT(int n){
		String t = "";
		for (int i = 0; i < n; i++) {
			t += "\t";
		}
		return t;
	}
	
	/**
	 * return content of file
	 * 
	 * @param locationPath
	 * @param tableName
	 * @param type
	 * @param fields
	 * @return
	 */
	public String getContent(String locationPath, String tableName, String type, String[][] fields){
		this.fields = fields;
		this.tableName = tableName;
		mapNameProperties = getNameForProperties(tableName);
		String content = "";
		BufferedReader br = null;

		try {
			

			String sCurrentLine;

			br = new BufferedReader(new FileReader(locationPath + "\\template\\jump\\"+type+".jump"));

			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = repleceNameProperties(sCurrentLine);
				content += sCurrentLine + "\n";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		}
		return content;
	}
	
	/**
	 * name for set data in .jump file
	 * @param tableName2
	 * @return
	 */
	private Map<String, String> getNameForProperties(String tableName2) {
		Map<String, String> map = new HashMap<>();
		map.put("modelName", WordUtils.capitalize(tableName2));
		map.put("nameCapitalize", WordUtils.capitalize(tableName2));
		map.put("nameLowerCase", tableName2.toLowerCase());
		map.put("tableName", tableName2);
		for(int i=0;i<fields.length;i++){
			if(fields[i][3].equals("YES")){
				map.put("namePrimaryKey", WordUtils.capitalize(fields[i][0]));
				map.put("typePrimaryKey", getTypeVariable().get(fields[i][1]));
			}
		}
		
		return map;
	}

	private String repleceNameProperties(String sCurrentLine) {
		Matcher m = Pattern.compile("\\$\\{(.*?)\\}").matcher(sCurrentLine);
		while(m.find()) {
		    String nameProperty = m.group(1);
		    switch (nameProperty) {
			case "loopAttribute":
				sCurrentLine = loopAttribute(sCurrentLine);
				break;
			case "loopGeterSetter":
				sCurrentLine = loopGeterSetter(sCurrentLine);
				break;
			case "loopMapSearch":
				sCurrentLine = loopMapSearch(sCurrentLine);
				break;
			case "loopTable":
				sCurrentLine = loopTable(sCurrentLine);
				break;
			case "loopFormAdd":
				sCurrentLine = loopFormAdd(sCurrentLine);
				break;
			case "loopFormUpdate":
				sCurrentLine = loopFormUpdate(sCurrentLine);
				break;
			case "loopAutowire":
				sCurrentLine = loopAutowire(sCurrentLine);
				break;
			case "loopForiegnList":
				sCurrentLine = loopForiegnList(sCurrentLine);
				break;
			case "loopImportService":
				sCurrentLine = loopImportService(sCurrentLine);
				break;
			case "loginout":
				sCurrentLine = loginout(sCurrentLine);
				break;
			case "servicedao":
				sCurrentLine = servicedao(sCurrentLine);
				break;
			case "serviceimpl":
				sCurrentLine = serviceImpl(sCurrentLine);
				break;
			case "daoimpl":
				sCurrentLine = daoImpl(sCurrentLine);
				break;
			default:
				if(mapNameProperties.containsKey(nameProperty)){
					sCurrentLine = sCurrentLine.replace("${"+nameProperty+"}", mapNameProperties.get(nameProperty) );
				}else{
					return sCurrentLine;
				}
				
				break;
			}
		}
		
		return sCurrentLine;
	}


	private String daoImpl(String sCurrentLine) {
		sCurrentLine = "";
		if(tableName.equals("juser")){
			
			sCurrentLine += wT(1)+"@Override\n";
			sCurrentLine += wT(1)+"public Juser doLogin(String username, String password) {\n";
			sCurrentLine += wT(2)+"Criteria criteria = session.getCurrentSession().createCriteria(Juser.class);\n";
			sCurrentLine += wT(2)+"criteria.add(Restrictions.eq(\"username\", username));\n";
			sCurrentLine += wT(2)+"criteria.add(Restrictions.eq(\"password\", password));\n";
			sCurrentLine += wT(2)+"return (Juser) criteria.uniqueResult();\n";
			sCurrentLine += wT(1)+"}\n";
			
			
			return sCurrentLine;
		}else{
			return sCurrentLine;
		}
	}

	private String serviceImpl(String sCurrentLine) {
		sCurrentLine = "";
		if(tableName.equals("juser")){
			
			sCurrentLine += wT(1)+"@Transactional\n";
			sCurrentLine += wT(1)+"public Juser doLogin(String code, String password) {\n";
			sCurrentLine += wT(2)+"return juserDao.doLogin(code, password);\n";
			sCurrentLine += wT(1)+"}\n";
			return sCurrentLine;
		}else{
			return sCurrentLine;
		}
	}

	private String servicedao(String sCurrentLine) {
		sCurrentLine = "";
		if(tableName.equals("juser")){
			
			sCurrentLine += wT(1)+"public Juser doLogin(String code, String password);\n";
			
			return sCurrentLine;
		}else{
			return sCurrentLine;
		}
	}

	private String loginout(String sCurrentLine) {
		sCurrentLine = "";
		if(tableName.equals("juser")){
			
			sCurrentLine += wT(1)+"@RequestMapping(value = \"/login.do\", method = RequestMethod.POST)\n";
			sCurrentLine += wT(6)+"public String doLogin(HttpSession session, @RequestParam(\"username\") String username, @RequestParam(\"password\") String password){\n";
				
			sCurrentLine += wT(2)+"Juser juser = juserService.doLogin(username, password);\n";
			sCurrentLine += wT(2)+"if(juser != null){\n";
			sCurrentLine += wT(3)+"session.setAttribute(\"name\", juser.getUsername());\n";
			sCurrentLine += wT(3)+"session.setAttribute(\"id\", juser.getId());\n";
			sCurrentLine += wT(3)+"return \"redirect:/\";\n";
			sCurrentLine += wT(2)+"}else{\n";
			sCurrentLine += wT(3)+"return \"redirect:/\";\n";
			sCurrentLine += wT(2)+"}\n";
			sCurrentLine += wT(1)+"}\n";
			
			
			sCurrentLine += wT(1)+"@RequestMapping(value = \"/logout.do\", method = RequestMethod.GET)\n";
			sCurrentLine += wT(1)+"public String doLogin(HttpSession session){\n";
			sCurrentLine += wT(2)+"session.invalidate();\n";	
			sCurrentLine += wT(2)+"return \"redirect:/\";\n";
			sCurrentLine += wT(1)+"};\n";
			
			return sCurrentLine;
		}else{
			return sCurrentLine;
		}
		
	}

	private String loopImportService(String sCurrentLine) {
		sCurrentLine = "";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += "import com.spring.service."+WordUtils.capitalize(fields[i][4])+"Service;\n";
			}
		}
		return sCurrentLine;
	}

	private String loopForiegnList(String sCurrentLine) {
		sCurrentLine = "";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += "\t\tmap.put(\""+fields[i][4].toLowerCase()+"List\", "+fields[i][4].toLowerCase()+
						"Service.getAll"+WordUtils.capitalize(fields[i][4])+"());\n";
			}
		}
		return sCurrentLine;
	}

	private String loopAutowire(String sCurrentLine) {
		sCurrentLine = "";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += "\t@Autowired\n";
				sCurrentLine += "\tprivate "+WordUtils.capitalize(fields[i][4])+"Service "+fields[i][4].toLowerCase()+
									"Service;\n";
			}
		}
		return sCurrentLine;
	}

	/*
	 * just for test.
	 */
	public static void main(String[] args) {
		Write w = new Write();
		String[][] ss = new String[][]{{"id", "INTEGER", "YES"},{"name","VARCHAR", "NO"}};
		w.getContent(System.getProperty("user.dir"), "customer", "view-add", ss);
	}

	private String loopFormAdd(String sCurrentLine) {
		sCurrentLine = "";
		sCurrentLine += wT(6)+"<form:form action=\"${addAction}\" method=\"POST\" commandName=\""+tableName.toLowerCase()+"\" class=\"form\">\n";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(7)+"<div class=\"form-group\">\n";
				sCurrentLine += wT(8)+"<form:label path=\""+fields[i][4].toLowerCase()+"."+fields[i][6].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n";
				sCurrentLine += wT(8)+"<form:select path=\""+fields[i][4].toLowerCase()+"."+fields[i][6].toLowerCase()+"\" class=\"form-control\">\n";
				sCurrentLine += wT(9)+"<form:options items=\"${"+fields[i][4].toLowerCase()+"List}\" itemValue=\""+fields[i][6].toLowerCase()+"\" itemLabel=\""+fields[i][5].toLowerCase()+"\"  />\n";
				sCurrentLine += wT(8)+"</form:select>";
				sCurrentLine += wT(7)+"</div>\n";
			}else{
				sCurrentLine += wT(7)+"<div class=\"form-group\">\n";
				sCurrentLine += wT(8)+"<form:label path=\""+fields[i][0].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n";
				if(getTypeHtml().get(fields[i][1]).equals("textarea")){
					sCurrentLine += wT(8)+"<form:textarea path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\"/>\n";
				}else{
					sCurrentLine += wT(8)+"<form:input path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\" type=\""+getTypeHtml().get(fields[i][1])+"\" />\n";
				}
				
				sCurrentLine += wT(7)+"</div>\n";
			}
		}
		
		sCurrentLine += wT(7)+"<input type=\"submit\" name=\"action\" value=\"Add\" />\n";
		sCurrentLine += wT(6)+"</form:form>\n";
		
		return sCurrentLine;
	}

	private String loopFormUpdate(String sCurrentLine) {
		sCurrentLine = "";
		sCurrentLine += wT(6)+"<form:form action=\"${addAction}\" method=\"POST\" commandName=\""+tableName.toLowerCase()+"\" class=\"form\">\n";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(7)+"<div class=\"form-group\">\n";
				sCurrentLine += wT(8)+"<form:label path=\""+fields[i][4].toLowerCase()+"."+fields[i][6].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n";
				sCurrentLine += wT(8)+"<form:select path=\""+fields[i][4].toLowerCase()+"."+fields[i][6].toLowerCase()+"\" class=\"form-control\">\n";
				sCurrentLine += wT(9)+"<form:options items=\"${"+fields[i][4].toLowerCase()+"List}\" itemValue=\""+fields[i][6].toLowerCase()+"\" itemLabel=\""+fields[i][5].toLowerCase()+"\"  />\n";
				sCurrentLine += wT(8)+"</form:select>";
				sCurrentLine += wT(7)+"</div>\n";
			}else{
				sCurrentLine += wT(7)+"<div class=\"form-group\">\n";
				sCurrentLine += wT(8)+"<form:label path=\""+fields[i][0].toLowerCase()+"\">"+WordUtils.capitalize(fields[i][0])+": </form:label>\n";
				if(getTypeHtml().get(fields[i][1]).equals("textarea")){
					sCurrentLine += wT(8)+"<form:textarea path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\"/>\n";
				}else{
					sCurrentLine += wT(8)+"<form:input path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\" type=\""+getTypeHtml().get(fields[i][1])+"\" />\n";
				}
				
				sCurrentLine += wT(7)+"</div>\n";
			}
		}
		
		sCurrentLine += wT(7)+"<input type=\"submit\" name=\"action\" value=\"Edit\" />\n";
		sCurrentLine += wT(6)+"</form:form>\n";
		
		return sCurrentLine;
	}
	
	private String loopMapSearch(String sCurrentLine) {
		sCurrentLine = "";
		// loop Map Search
		for(int i=0; i<fields.length; i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(4)+".createCriteria(\""+fields[i][4].toLowerCase()+"\").add(Example.create("+tableName.toLowerCase()+".get"
							+WordUtils.capitalize(fields[i][4])+"()).enableLike(MatchMode.ANYWHERE))\n";
			}
		}
		return sCurrentLine;
	}
	
	private String loopGeterSetter(String sCurrentLine) {
		sCurrentLine = "";
		// writes getter setter
		for(int i=0; i<fields.length; i++){
			
			if(!fields[i][4].equals("NO")){
				//getter
				sCurrentLine += "\tpublic "+WordUtils.capitalize(fields[i][4])+" get"+WordUtils.capitalize(fields[i][4].toLowerCase())+"() {\n";
				sCurrentLine += "\t\treturn this."+fields[i][4].toLowerCase()+";\n";
				sCurrentLine += "\t}\n\n";
				
				//setter
				sCurrentLine += "\tpublic void set"+WordUtils.capitalize(fields[i][4].toLowerCase())
							+"("+WordUtils.capitalize(fields[i][4])+" "
							+fields[i][4].toLowerCase()+") {\n";
				sCurrentLine += "\t\tthis."+fields[i][4].toLowerCase()+" = "+fields[i][4].toLowerCase()+";\n";
				sCurrentLine += "\t}\n\n";
			}else{
				//getter
				sCurrentLine += "\tpublic "+getTypeVariable().get(fields[i][1])+" get"+WordUtils.capitalize(fields[i][0].toLowerCase())+"() {\n";
				sCurrentLine += "\t\treturn this."+fields[i][0].toLowerCase()+";\n";
				sCurrentLine += "\t}\n\n";
				
				//setter
				sCurrentLine += "\tpublic void set"+WordUtils.capitalize(fields[i][0].toLowerCase())
							+"("+getTypeVariable().get(fields[i][1])+" "
							+fields[i][0].toLowerCase()+") {\n";
				sCurrentLine += "\t\tthis."+fields[i][0].toLowerCase()+" = "+fields[i][0].toLowerCase()+";\n";
				sCurrentLine += "\t}\n\n";
			}
			
			
		}
		return sCurrentLine;
		
	}

	private String loopAttribute(String sCurrentLine) {
		sCurrentLine = "";
		for(int i=0; i<fields.length; i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += "\t@ManyToOne\n";
				sCurrentLine += "\t@JoinColumn(name = \""+fields[i][0]+"\")\n";
				sCurrentLine += "\tprivate "+WordUtils.capitalize(fields[i][4])+" "+fields[i][4].toLowerCase()+";\n\n";
			}else if(fields[i][3].equals("YES")){
				sCurrentLine += "\t@Id\n";
				sCurrentLine += "\t@Column(name = \""+fields[i][0]+"\")\n";
				
				if(fields[i][2].equals("YES")) sCurrentLine += "\t@GeneratedValue(strategy = GenerationType.AUTO)\n";
				
				sCurrentLine += "\tprivate "+getTypeVariable().get(fields[i][1])+" "+fields[i][0].toLowerCase()+";\n\n";
			}else{
				sCurrentLine += "\t@Column(name = \""+fields[i][0]+"\")\n";
				
				if(fields[i][2].equals("YES")) sCurrentLine += "\t@GeneratedValue(strategy = GenerationType.AUTO)\n";
				
				sCurrentLine += "\tprivate "+getTypeVariable().get(fields[i][1])+" "+fields[i][0].toLowerCase()+";\n\n";
			}
		}
		return sCurrentLine;		
	}
	
	private String loopTable(String sCurrentLine) {
		
		sCurrentLine = "";
		
		sCurrentLine += wT(6)+"<table class=\"table table-bordered table-responsive\">\n";
		// writes <thead>
		sCurrentLine += wT(7)+"<thead>\n";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(8)+"<th>"+WordUtils.capitalize(fields[i][4])+"</th>\n";
			}else{
				sCurrentLine += wT(8)+"<th>"+WordUtils.capitalize(fields[i][0])+"</th>\n";
			}
			
		}			
		sCurrentLine += wT(8)+"<th>manage</th>\n";
		
		// input search
		sCurrentLine += wT(9)+"<tr id=\"search\">\n";
		sCurrentLine += wT(9)+"<c:url var='addAction' value='/"+tableName.toLowerCase()+"/search' />\n";
		sCurrentLine += wT(9)+"<form:form action=\"${addAction}\" id=\""+tableName.toLowerCase()+
				"Search\"  method=\"POST\" modelAttribute=\""+tableName.toLowerCase()+
				"\" commandName=\""+tableName.toLowerCase()+"\">\n";
		for(int i=0; i<fields.length; i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(10)+"<td><form:input path=\""+fields[i][4].toLowerCase()+"."+
						fields[i][5].toLowerCase()+"\" class=\"form-control\" type=\"text\" /></td>\n";
			}else{
				sCurrentLine += wT(10)+"<td><form:input path=\""+fields[i][0].toLowerCase()+"\" class=\"form-control\" type=\""+getTypeHtml().get(fields[i][1])+"\" /></td>\n";
			}
			
		}
		sCurrentLine += wT(10)+"<td> </td>\n";
		sCurrentLine += wT(9)+"</form:form>\n";
		sCurrentLine += wT(8)+"</tr>\n";
		// end input search	
		sCurrentLine += wT(8)+"</thead>\n";
		// end thead
		sCurrentLine += wT(7)+"<tbody id=\"rows-content\">\n";
		sCurrentLine += wT(8)+"<c:forEach items=\"${"+tableName.toLowerCase()+"List}\" var=\""+tableName.toLowerCase()+"\">\n";
		sCurrentLine += wT(9)+"<tr>\n";
		for(int i=0;i<fields.length;i++){
			if(!fields[i][4].equals("NO")){
				sCurrentLine += wT(10)+"<td>${"+tableName.toLowerCase()+"."+fields[i][4].toLowerCase()+"."+fields[i][5].toLowerCase()+"}</td>\n";
			}else{
				sCurrentLine += wT(10)+"<td>${"+tableName.toLowerCase()+"."+fields[i][0].toLowerCase()+"}</td>\n";
			}
			
		}
		sCurrentLine += wT(10)+"<td>\n";
		sCurrentLine += wT(11)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/${"+tableName.toLowerCase()+"."+fields[0][0].toLowerCase()+"}/update' />\" >Update</a>\n";
		sCurrentLine += wT(11)+"<a href=\"<c:url value='/"+tableName.toLowerCase()+"/${"+tableName.toLowerCase()+"."+fields[0][0].toLowerCase()+"}/delete' />\" >Delete</a>\n";
		sCurrentLine += wT(10)+"</td>\n";
		sCurrentLine += wT(9)+"</tr>\n";
		sCurrentLine += wT(8)+"</c:forEach>\n";
		sCurrentLine += wT(7)+"</tbody>\n";
		sCurrentLine += wT(6)+"</table>\n";
		
		return sCurrentLine;
	}

}
