package jumpfamework.test;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		String[] tmp = path.split("\\\\");
		path = tmp[tmp.length-1];
		System.out.println(path);
		
		
	}
	
	
}
