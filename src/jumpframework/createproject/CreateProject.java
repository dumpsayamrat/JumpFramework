package jumpframework.createproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

public class CreateProject {
	
	
	
	private static String[][] DIR = new String[][]{
			{"src"},
			{"main"},
			{"java", "resources", "test", "webapp"},
			{"com"},
			{"spring"},
			{"controller", "dao", "model", "repository", "service"},
			{},
			{"WEB-INF","META-INF"},
			{"pages", "lib"},
			{},
			{}
	};
	
	private static String[][] FILE = new String[][]{
		{"build.gradle"},
		{},
		{},
		{},
		{},
		{},
		{"log4j.xml"},
		{"Theme"},
		{"web.xml", "jumpweb-servlet.xml"},
		{"header.jsp", "index.jsp", "about.jsp", "contact.jsp", "footer.jsp"},
		{"WelcomeController.java"}
	};
	
	private static String[] subProjectPath = new String[]{
		"",
		"\\src",
		"\\src\\main",
		"\\src\\main\\java",
		"\\src\\main\\java\\com",
		"\\src\\main\\java\\com\\spring",
		"\\src\\main\\resources",
		"\\src\\main\\webapp",
		"\\src\\main\\webapp\\WEB-INF",
		"\\src\\main\\webapp\\WEB-INF\\pages",
		"\\src\\main\\java\\com\\spring\\controller"
	};
	
	private static FileUtil fu;
	
	/**
	 * create project(create folder and file etc.) 
	 * 
	 * @param projectPath Project Location.
	 * @param connection Connection String for create jdbc.properties.
	 * @param user database user for create jdbc.properties. 
	 * @param pass database password for create jdbc.properties.
	 * @return true if success.
	 */
	public static boolean createProject(String projectPath, String connection, String user, String pass){
		
		for(int i=0;i<DIR.length;i++){
			 String path = projectPath+subProjectPath[i];
			 System.out.println("This is projectPath-> " + path);
			 File f = new File(path);
			 File[] fl = f.listFiles();
			 //System.out.println(DIR[i].length);
			 ArrayList<String> checkName = new ArrayList<String>();
			 for (int j = 0; j < fl.length; j++) {
				 checkName.add(fl[j].getName());
			 }
			 
			 for (int j = 0; j < DIR[i].length; j++) {
				 
				if(!checkName.contains(DIR[i][j])){
					FileUtil.createDirectory(DIR[i][j], path);
				}else{
					System.out.println(DIR[i][j]+" is exists.");
				}
			 }
			 
			 for (int j = 0; j < FILE[i].length; j++) {			 
					
				if(!checkName.contains(FILE[i][j])){
					try {
						
						switch (FILE[i][j]) {
						case "build.gradle":
							createBuildGradle(projectPath, path);
							break;

						default:
							FileUtil.copyFile((FILE[i][j]), path, projectPath);
							break;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}else{
					System.out.println(FILE[i][j]+" is exists.");
				}					
			 }
		 }
		createJdbc(projectPath,connection, user, pass);
			
		return true;
		
	}


	/**
	 * create bulid.gradle file 
	 * 
	 * @param projectPath use for get project's name
	 * @param path use for create file
	 */
	private static void createBuildGradle(String projectPath, String path) {
		fu = new FileUtil();
		File file = fu.createFile("build", path+"\\", "gradle");
		
		String[] tmp = projectPath.split("\\\\");
		String projectName = tmp[tmp.length-1];
		
		Map<String, String> param = new HashMap<>();		
		param.put("projectName", projectName);
		
		try { 
			FileWriter fw = new FileWriter(file);
			String content = fu.writeAsTemplate(projectPath, param, "build.gradle");
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}



	/**
	 * create HibernateConfig -> hibernate.cfg.xml in src.main.resources
	 * @param projectPath project location.
	 */
	public static void createHibernateConfig(String projectPath) {
		File f = new File(projectPath+"\\src\\main\\java\\com\\spring\\model");
		File[] fl = f.listFiles();
		
		fu = new FileUtil();
		File file = fu.createFile("hibernate.cfg", projectPath+"\\src\\main\\resources\\", "xml");
		
		try {
			FileWriter fw = new FileWriter(file);
			
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			fw.write("<!DOCTYPE hibernate-configuration PUBLIC\n");
			fw.write("\t\"-//Hibernate/Hibernate Configuration DTD//EN\"\n");
			fw.write("\t\"http://hibernate.org/dtd/hibernate-configuration-3.0.dtd\">\n\n");
			fw.write("<hibernate-configuration>\n");
			fw.write("\t<session-factory>\n");
			fw.write("\t\t<property name=\"hibernate.query.factory_class\">org.hibernate.hql.classic.ClassicQueryTranslatorFactory</property>\n");
			for(int i=0;i<fl.length;i++){
				String[] tmp = fl[i].getName().split("\\."); 
				fw.write("\t\t<mapping class=\"com.spring.model."+tmp[0]+"\" />\n");
			}
			fw.write("\t</session-factory>\n");
			fw.write("</hibernate-configuration>\n");
			
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	/**
	 * create jdbc.properties
	 * 
	 * @param projectPath project location.
	 * @param connection Connection String for create jdbc.properties.
	 * @param user database user for create jdbc.properties. 
	 * @param pass database password for create jdbc.properties.
	 */
	private static void createJdbc(String projectPath, String connection, String user, String pass) {
		fu = new FileUtil();
		File file = fu.createFile("jdbc", projectPath+"\\src\\main\\webapp\\WEB-INF\\", "properties");
		
		try {
			FileWriter fw = new FileWriter(file);
			
			fw.write("jdbc.driverClassName=com.mysql.jdbc.Driver\n");
			fw.write("jdbc.dialect=org.hibernate.dialect.MySQLDialect\n");
			fw.write("jdbc.databaseurl="+connection+"\n");
			fw.write("jdbc.username="+user+"\n");
			fw.write("jdbc.password="+pass);
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

