package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteDao extends Write {
	
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
	 * the dao file
	 */
	private File file;
	
	private String subPath = "\\src\\main\\java\\com\\spring\\dao\\";
	
	/**
	 * create and write file dao.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteDao(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createDao(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		file = fu.createFile(WordUtils.capitalize(tableName)+"Dao", path+subPath, "java");
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("package com.spring.dao;\n\n");
			fw.write("import java.util.List;\n\n");
			fw.write("import com.spring.model."+WordUtils.capitalize(tableName)+";\n\n");
			fw.write("public interface "+WordUtils.capitalize(tableName)+"Dao {\n\n");
			fw.write("\tpublic void add("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+");\n");
			fw.write("\tpublic void edit("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+");\n");		
			fw.write("\tpublic void delete(int "+tableName.toLowerCase()+"Id);\n");
			fw.write("\tpublic "+WordUtils.capitalize(tableName)+" get"+WordUtils.capitalize(tableName)+"(int "+tableName.toLowerCase()+"Id);\n");
			fw.write("\tpublic List getAll"+WordUtils.capitalize(tableName)+"();\n\n");
			fw.write("}");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
