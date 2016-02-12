package jumpframework.createproject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
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
	
	
	
	
}
