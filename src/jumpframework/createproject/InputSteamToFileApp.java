package jumpframework.createproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipFile;

public class InputSteamToFileApp  {
	
	
	public void extactFile(String projectPath){
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			// read this file into InputStream
			inputStream = this.getClass().getClassLoader().getResourceAsStream("template.zip");

			// write the inputStream to a FileOutputStream
			outputStream = 
	                    new FileOutputStream(new File(projectPath+"\\template.zip"));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			
			
	    	UnZip.unzipFileIntoDirectory(new ZipFile(projectPath+"\\template.zip"),new File(projectPath));

			System.out.println("Done!");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
	
    public static void main(String[] args) {

	
    }
}