package jumpframework.createproject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
	/**
	 * Creates the directory named.
	 * @param filename directory name.
	 * @param path A pathname string.
	 */
	public static void createDirectory(String filename, String path){
		new File(path+"\\"+filename).mkdirs();
		System.out.println(path+"\\"+filename+" Created.");
	}
	
	
	/**
	 * Copy file in template folder.
	 * @param filename = file's name in template folder.
	 * @param path = destination path.
	 */
	public static void copyFile(String filename, String path, String projectPath) throws IOException{
		//System.out.println("create "+filename+" file");
		String templatePath = projectPath+"\\"+"template";
		File src = new File(templatePath+"\\"+filename);
		File dest = new File(path+"\\"+filename);
		
		if(filename.equals("Theme")){
			FileUtils.copyDirectory(src, dest);
		}else{
			FileUtils.copyFile(src, dest);
		}
	}
	
	/**
	 * create newly file;
	 * @param filename = file's name.
	 * @param path = destination path.
	 * @param extension = extension's file.
	 */
	public File createFile(String filename, String path, String extension){
		File javaFile = new File(path, filename+"."+extension);
		if(!javaFile.exists()){
			
			try {
				javaFile.createNewFile();
				return javaFile;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return javaFile;
	}
	
	/**
	 * Method for check Project Location.
	 * @param path Project Location.
	 * @return return true if path is directory.
	 */
	 public static boolean checkLocation(String path){
		 return new File(path).isDirectory();
		
	 }

	 /**
	  * delete directory 
	  * 
	  * @param path target path for delete
	  */
	public static void deleteDirectory(String path) {
		try {
			FileUtils.forceDelete(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * delete file
	 * @param path target file for delete
	 */
	public static void deleteFile(String path) {
		new File(path).delete();
		System.out.println(path+" deleted.");
		
	}

	
	
	
	
}
