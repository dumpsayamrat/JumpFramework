package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteModel extends Write {
	
	/*
	 * table's name 
	 */
	private String tableName;
	
	/*
	 * all of field in table : array 
	 */
	private String[][] fields;
	
	/*
	 * Project's path only.
	 */
	private String path;
	
	/*
	 * the model file
	 */
	private File file;
	
	private String subPath = "\\src\\main\\java\\com\\spring\\model\\";

	/**
	 * create and write file Model.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteModel(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}
	
	/**
	 * create and write file 
	 */	
	public void createModel(){
		createFile();
		writeFile();
	}
	
	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		file = fu.createFile(WordUtils.capitalize(tableName), path+subPath, "java");
	}

	/*
	 * write file each lines 
	 */
	private void writeFile() {
		
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("package com.spring.model;\n\n");
			fw.write("import javax.persistence.Column;\n");
			fw.write("import javax.persistence.Entity;\n");
			fw.write("import javax.persistence.GeneratedValue;\n");
			fw.write("import javax.persistence.GenerationType;\n");
			fw.write("import javax.persistence.Id;\n");
			fw.write("import javax.persistence.Table;\n\n");
			fw.write("@Entity\n");
			fw.write("@Table(name = \""+tableName+"\")\n");
			fw.write("public class "+WordUtils.capitalize(tableName)+" {\n\n");
			
			//  writes properties || TODO This not finish, it should check primary Key;
			for(int i=0; i<fields.length; i++){
				if(fields[i][2].equals("YES")){
					fw.write("\t@Id\n");
					fw.write("\t@Column(name = \""+fields[i][0]+"\")\n");
					fw.write("\t@GeneratedValue(strategy = GenerationType.AUTO)\n");
					fw.write("\tprivate "+getTypeVariable().get(fields[i][1])+" "+fields[i][0].toLowerCase()+";\n\n");
				}else{
					fw.write("\t@Column(name = \""+fields[i][0]+"\")\n");
					fw.write("\tprivate "+getTypeVariable().get(fields[i][1])+" "+fields[i][0].toLowerCase()+";\n\n");
				}
			}
			
			// writes getter setter
			for(int i=0; i<fields.length; i++){
				//getter
				fw.write("\tpublic "+getTypeVariable().get(fields[i][1])+" get"+WordUtils.capitalize(fields[i][0])+"() {\n");
				fw.write("\t\treturn "+fields[i][0].toLowerCase()+";\n");
				fw.write("\t}\n\n");
				
				//setter
				fw.write("\tpublic void set"+WordUtils.capitalize(fields[i][0])
							+"("+getTypeVariable().get(fields[i][1])+" "
							+fields[i][0].toLowerCase()+") {\n");
				fw.write("\t\tthis."+fields[i][0].toLowerCase()+" = "+fields[i][0].toLowerCase()+";\n");
				fw.write("\t}\n\n");
				
			}
			
			fw.write("\n}");
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	

}
