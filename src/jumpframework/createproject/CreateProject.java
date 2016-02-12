package jumpframework.createproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;

public class CreateProject {
	
	//static String projectPath = System.getProperty("user.dir");
	//static String projectPath = "F:\\webAppJava\\new";
	
	static String[][] DIR = new String[][]{
			{"src"},
			{"main"},
			{"java", "resources", "test", "webapp"},
			{"com"},
			{"spring"},
			{"controller", "dao", "model", "repository", "service"},
			{},
			{"WEB-INF","META-INF"},
			{"pages", "lib"},
			{},
			{}
	};
	
	static String[][] FILE = new String[][]{
		{"build.gradle"},
		{},
		{},
		{},
		{},
		{},
		{"log4j.xml","hibernate.cfg.xml"},
		{"Theme"},
		{"web.xml", "jumpweb-servlet.xml"},
		{"includes.jsp", "index.jsp", "about.jsp", "contact.jsp"},
		{"WelcomeController.java"}
	};
	
	static String[] subProjectPath = new String[]{
		"",
		"\\src",
		"\\src\\main",
		"\\src\\main\\java",
		"\\src\\main\\java\\com",
		"\\src\\main\\java\\com\\spring",
		"\\src\\main\\resources",
		"\\src\\main\\webapp",
		"\\src\\main\\webapp\\WEB-INF",
		"\\src\\main\\webapp\\WEB-INF\\pages",
		"\\src\\main\\java\\com\\spring\\controller"
	};
	
	
	
	
	public static boolean createProject(String projectPath, String connection, String user, String pass){
		
		for(int i=0;i<DIR.length;i++){
			 String path = projectPath+subProjectPath[i];
			 System.out.println("This is projectPath-> " + path);
			 File f = new File(path);
			 File[] fl = f.listFiles();
			 //System.out.println(DIR[i].length);
			 ArrayList<String> checkName = new ArrayList<String>();
			 for (int j = 0; j < fl.length; j++) {
				 checkName.add(fl[j].getName());
			 }
			 
			 for (int j = 0; j < DIR[i].length; j++) {
				 
				if(!checkName.contains(DIR[i][j])){
					FileUtil.createDirectory(DIR[i][j], path);
				}else{
					System.out.println(DIR[i][j]+" is exists.");
				}
			 }
			 
			 for (int j = 0; j < FILE[i].length; j++) {			 
					
				if(!checkName.contains(FILE[i][j])){
					try {
						FileUtil.copyFile((FILE[i][j]), path, projectPath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}else{
					System.out.println(FILE[i][j]+" is exists.");
				}					
			 }
		 }	
		
		createJdbc(projectPath,connection, user, pass);	
			
			
		return true;
		
	}




	private static void createJdbc(String projectPath, String connection, String user, String pass) {
		FileUtil fu = new FileUtil();
		File file = fu.createFile("jdbc", projectPath+"\\src\\main\\webapp\\WEB-INF\\", "properties");
		
		try {
			FileWriter fw = new FileWriter(file);
			
			fw.write("jdbc.driverClassName=com.mysql.jdbc.Driver\n");
			fw.write("jdbc.dialect=org.hibernate.dialect.MySQLDialect\n");
			fw.write("jdbc.databaseurl="+connection+"\n");
			fw.write("jdbc.username="+user+"\n");
			fw.write("jdbc.password="+pass);
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

