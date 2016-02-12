package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteServiceImpl extends Write {

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
	 * the serviceImpl file
	 */
	private File file;
	
	private String subPath = "\\src\\main\\java\\com\\spring\\repository\\";
	
	/**
	 * create and write file serviceImpl.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteServiceImpl(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createServiceImpl(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		file = fu.createFile(WordUtils.capitalize(tableName)+"ServiceImpl", path+subPath, "java");
		
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("package com.spring.repository;\n\n");
			fw.write("import java.util.List;\n\n");
			fw.write("import org.springframework.beans.factory.annotation.Autowired;\n");
			fw.write("import org.springframework.stereotype.Service;\n");
			fw.write("import org.springframework.transaction.annotation.Transactional;\n\n");
			fw.write("import com.spring.dao."+WordUtils.capitalize(tableName)+"Dao;\n");
			fw.write("import com.spring.model."+WordUtils.capitalize(tableName)+";\n");
			fw.write("import com.spring.service."+WordUtils.capitalize(tableName)+"Service;\n\n");
			fw.write("@Service\n");
			fw.write("public class "+WordUtils.capitalize(tableName)+"ServiceImpl implements "+WordUtils.capitalize(tableName)+"Service {\n\n");
			fw.write("\t@Autowired\n");
			fw.write("\tprivate "+WordUtils.capitalize(tableName)+"Dao "+tableName.toLowerCase()+"Dao;\n\n");
			
			fw.write("\t@Transactional\n");
			fw.write("\tpublic void add("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+") {\n");
			fw.write("\t\t"+tableName.toLowerCase()+"Dao.add("+tableName.toLowerCase()+");\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Transactional\n");
			fw.write("\tpublic void edit("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+") {\n");
			fw.write("\t\t"+tableName.toLowerCase()+"Dao.edit("+tableName.toLowerCase()+");\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Transactional\n");
			fw.write("\tpublic void delete(int "+tableName.toLowerCase()+"Id) {\n");
			fw.write("\t\t"+tableName.toLowerCase()+"Dao.delete("+tableName.toLowerCase()+"Id);\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Transactional\n");
			fw.write("\tpublic "+WordUtils.capitalize(tableName)+" get"+WordUtils.capitalize(tableName)+"(int "+tableName.toLowerCase()+"Id) {\n");
			fw.write("\t\treturn "+tableName.toLowerCase()+"Dao.get"+WordUtils.capitalize(tableName)+"("+tableName.toLowerCase()+"Id);\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Transactional\n");
			fw.write("\tpublic List getAll"+WordUtils.capitalize(tableName)+"() {\n");
			fw.write("\t\treturn "+tableName.toLowerCase()+"Dao.getAll"+WordUtils.capitalize(tableName)+"();\n");
			fw.write("\t}\n\n");
			
			fw.write("}");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
