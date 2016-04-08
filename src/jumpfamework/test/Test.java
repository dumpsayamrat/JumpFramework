package jumpfamework.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

public class Test {
	private int num;
	
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		String[] tmp = path.split("\\\\");
		path = tmp[tmp.length-1];
		System.out.println(path);
		String tableName = "employee";
		String sCurrentLine = "\t\tif(String.valueOf("+tableName.toLowerCase()+".get"+WordUtils.capitalize("de_id")+"()) != null) mapSearch.put(\""+"de_id".toLowerCase()+
				"\", String.valueOf("+tableName.toLowerCase()+".get"+WordUtils.capitalize("de_id")+"()));\n";
		//int num = 0;
		Test t = new Test();
		t.setNum(5);
		if(String.valueOf(t.getNum()).toString()!=null){
			System.out.println(!String.valueOf(t.getNum()).toString().isEmpty());
			System.out.println(sCurrentLine);
		}
		
		
		
	}
	
	public int getNum(){
		return this.num;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	
}
