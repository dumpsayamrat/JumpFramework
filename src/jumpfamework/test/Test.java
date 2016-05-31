package jumpfamework.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import jumpframework.createproject.MySQL;

public class Test {
	private int num;
	
	public static void main(String[] args) {
		
		MySQL mySql = new MySQL("jdbc:mysql://localhost/employee", "spring", "1234");
		
		for (String t : mySql.getTablesName()) {
			System.out.println(t);
			String[][] s = mySql.getFields(t);
			for(int i=0;i<s.length;i++){
				System.out.println("\t"+s[i][0]+":"+s[i][1]+":"+s[i][2]+":"+s[i][3]+":"+s[i][4]+":"+s[i][5]+":"+s[i][6]);
			}
		}
		
		
		
	}
	
	public int getNum(){
		return this.num;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	
}
