package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteDaoImpl extends Write{

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
	 * the daoImpl file
	 */
	private File file;
	
	private String subPath = "\\src\\main\\java\\com\\spring\\repository\\";
	
	/**
	 * create and write file daoImpl.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteDaoImpl(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createDaoImpl(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		file = fu.createFile(WordUtils.capitalize(tableName)+"DaoImpl", path+subPath, "java");
		
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write("package com.spring.repository;\n\n");
			fw.write("import java.util.List;\n\n");
			fw.write("import org.hibernate.SessionFactory;\n");
			fw.write("import org.springframework.beans.factory.annotation.Autowired;\n");
			fw.write("import org.springframework.stereotype.Repository;\n\n");
			fw.write("import com.spring.dao."+WordUtils.capitalize(tableName)+"Dao;\n");
			fw.write("import com.spring.model."+WordUtils.capitalize(tableName)+";\n\n");
			fw.write("@Repository\n");
			fw.write("public class "+WordUtils.capitalize(tableName)+"DaoImpl implements "+WordUtils.capitalize(tableName)+"Dao {\n\n");
			fw.write("\t@Autowired\n");
			fw.write("\tprivate SessionFactory session;\n\n");
			
			fw.write("\t@Override\n");
			fw.write("\tpublic void add("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+") {\n");
			fw.write("\t\tsession.getCurrentSession().save("+tableName.toLowerCase()+");\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Override\n");
			fw.write("\tpublic void edit("+WordUtils.capitalize(tableName)+" "+tableName.toLowerCase()+") {\n");
			fw.write("\t\tsession.getCurrentSession().update("+tableName.toLowerCase()+");\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Override\n");
			fw.write("\tpublic void delete(int "+tableName.toLowerCase()+"Id) {\n");
			fw.write("\t\tsession.getCurrentSession().delete(get"+WordUtils.capitalize(tableName)+"("+tableName.toLowerCase()+"Id));\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Override\n");
			fw.write("\tpublic "+WordUtils.capitalize(tableName)+" get"+WordUtils.capitalize(tableName)+"(int "+tableName.toLowerCase()+"Id) {\n");
			fw.write("\t\treturn ("+WordUtils.capitalize(tableName)+") session.getCurrentSession().get("+WordUtils.capitalize(tableName)+".class, "+tableName.toLowerCase()+"Id);\n");
			fw.write("\t}\n\n");
			
			fw.write("\t@Override\n");
			fw.write("\tpublic List getAll"+WordUtils.capitalize(tableName)+"() {\n");
			fw.write("\t\treturn session.getCurrentSession().createQuery(\"from "+WordUtils.capitalize(tableName)+"\").list();\n");
			fw.write("\t}\n\n");
			
			fw.write("}");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
