package jumpframework.createproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		{"jdbc.properties", "web.xml", "jumpweb-servlet.xml"},
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
	
	
	
	
	public static boolean createProject(String projectPath){
		
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
		
		
		return true;
		
	}
}

