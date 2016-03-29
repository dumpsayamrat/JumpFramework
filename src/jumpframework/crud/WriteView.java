package jumpframework.crud;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteView extends Write {
	
	/**
	 * type of file
	 */
	final static private String type = "view";

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
	 * the view file
	 */
	private File listFile,addFile,updateFile;
	
	private String subPath = "\\src\\main\\webapp\\WEB-INF\\pages\\";
	
	/**
	 * create and write file view.
	 * @param tableName = table's name
	 * @param fields = 2d array -> field's name, data type, is AUTOINCREMENT
	 * @param path = Project's path for create file
	 */
	public WriteView(String tableName,String[][] fields, String path) {
		this.tableName = tableName;
		this.fields = fields;
		this.path = path;
	}

	/**
	 * create and write file 
	 */	
	public void createView(){
		createFile();
		writeFile();
	}	
	

	/*
	 * create file from FileUtil ;using createFile method
	 */
	private void createFile() {
		
		FileUtil fu = new FileUtil();
		FileUtil.createDirectory(tableName.toLowerCase(), path+subPath);
		subPath += tableName.toLowerCase()+"\\";
		listFile = fu.createFile("list", path+subPath, "jsp");
		addFile = fu.createFile("add", path+subPath, "jsp");
		updateFile = fu.createFile("update", path+subPath, "jsp");
	}
	
	/*
	 * write file each lines 
	 */
	private void writeFile() {
		
		createListView();
		createAddView();
		createUpdateView();		
		
	}

	/*
	 * create List all table | list.jsp
	 */
	private void createListView() {
		try {
			FileWriter fw = new FileWriter(listFile);
			String content = getContent(path, tableName, "view-list", fields);
			fw.write(content);			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	/*
	 * create add data  | add.jsp
	 */
	private void createAddView() {
		try {
			FileWriter fw = new FileWriter(addFile);
			String content = getContent(path, tableName, "view-add", fields);
			fw.write(content);	
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	/*
	 * create update table | update.jsp
	 */
	private void createUpdateView() {
		try {
			FileWriter fw = new FileWriter(updateFile);
			String content = getContent(path, tableName, "view-update", fields);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
