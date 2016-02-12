package jumpframework.createproject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class UnZip
{
    List<String> fileList;
    private static final String INPUT_ZIP_FILE = "C:\\MyFile.zip";
    private static final String OUTPUT_FOLDER = "C:\\outputzip";
		
    public static void main( String[] args )
    {
    	String projectPath="F:\\runtime-EclipseApplication\\qwe";
    	FileUtil.deleteFile(projectPath+"\\template.zip");
    	/*UnZip unZip = new UnZip();
    	unZip.unZipIt("F:\\runtime-EclipseApplication\\power\\poo.zip",
    			"C:\\Users\\56023_000\\Documents\\GitHub\\JumpFramework\\foo\\");*/
    	/*try {
			unzipFileIntoDirectory(new ZipFile("F:\\runtime-EclipseApplication\\power\\poo.zip"), new File("C:\\Users\\56023_000\\Documents\\GitHub\\JumpFramework\\foo\\"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	/* try {
    	        ZipFile zipFile = new ZipFile("F:\\runtime-EclipseApplication\\power\\poo.zip");
    	        zipFile.extractAll("C:\\Users\\56023_000\\Documents\\GitHub\\JumpFramework\\foo\\");
    	    } catch (ZipException e) {
    	        e.printStackTrace();
    	    }*/
    }
    
    /**
     * Unzip it
     * @param zipFile input zip file
     * @param jiniHomeParentDir zip file output folder
     */
    public static void unzipFileIntoDirectory(ZipFile zipFile, File jiniHomeParentDir) {
        Enumeration files = zipFile.entries();
        File f = null;
        FileOutputStream fos = null;
        
        while (files.hasMoreElements()) {
          try {
            ZipEntry entry = (ZipEntry) files.nextElement();
            InputStream eis = zipFile.getInputStream(entry);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            System.out.println(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());
            f = new File(jiniHomeParentDir.getAbsolutePath() + File.separator + entry.getName());
            
            if (entry.isDirectory()) {
              f.mkdirs();
              continue;
            } else {
              f.getParentFile().mkdirs();
              f.createNewFile();
            }
            
            fos = new FileOutputStream(f);
      
            while ((bytesRead = eis.read(buffer)) != -1) {
              fos.write(buffer, 0, bytesRead);
            }
          } catch (IOException e) {
            e.printStackTrace();
            continue;
          } finally {
            if (fos != null) {
              try {
                fos.close();
              } catch (IOException e) {
                // ignore
              }
            }
          }
        }
      }
   
}