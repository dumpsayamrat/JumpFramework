package jumpframework.crud;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;




import jumpframework.createproject.FileUtil;

import org.apache.commons.lang3.text.WordUtils;

public class WriteModel extends Write {
	
	/**
	 * type of file
	 */
	final static private String type = "model";
	
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
			String content = getContent(path, tableName, type, fields);
			fw.write(content);
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	

}
