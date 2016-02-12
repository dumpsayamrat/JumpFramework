package jumpframework.crud;

import java.util.HashMap;

public abstract class Write {
	
	/**
	 * convert SQL data type to JAVA Type variable
	 */
	protected HashMap<String, String> getTypeVariable(){
		HashMap<String, String> typeVariable = new HashMap<String, String>();		
		typeVariable.put("BIT","byte");
		typeVariable.put("TINYINT","byte");
		typeVariable.put("SMALLINT","short");
		typeVariable.put("INTEGER","int");
		typeVariable.put("BIGINT","long");
		typeVariable.put("FLOAT","float");
		typeVariable.put("REAL","double");
		typeVariable.put("DOUBLE","double");
		typeVariable.put("NUMERIC","double");
		typeVariable.put("DECIMAL","double");
		typeVariable.put("CHAR","String");
		typeVariable.put("VARCHAR","String");
		typeVariable.put("LONGVARCHAR","String");
		typeVariable.put("DATE","String");
		typeVariable.put("TIME","String");
		typeVariable.put("TIMESTAMP","String");
		typeVariable.put("BINARY","String");
		typeVariable.put("VARBINARY","String");
		typeVariable.put("LONGVARBINARY","String");
		typeVariable.put("BOOLEAN","String");
		return typeVariable;
	}
	
	/**
	 * convert SQL data type to Html type
	 */
	protected HashMap<String, String> getTypeHtml(){
		HashMap<String, String> typeVariable = new HashMap<String, String>();		
		typeVariable.put("BIT","number");
		typeVariable.put("TINYINT","number");
		typeVariable.put("SMALLINT","number");
		typeVariable.put("INTEGER","number");
		typeVariable.put("BIGINT","number");
		typeVariable.put("FLOAT","number");
		typeVariable.put("REAL","number");
		typeVariable.put("DOUBLE","number");
		typeVariable.put("NUMERIC","number");
		typeVariable.put("DECIMAL","number");
		typeVariable.put("CHAR","text");
		typeVariable.put("VARCHAR","text");
		typeVariable.put("LONGVARCHAR","textarea");
		typeVariable.put("DATE","date");
		typeVariable.put("TIME","time");
		typeVariable.put("TIMESTAMP","datetime");
		typeVariable.put("BINARY","text");
		typeVariable.put("VARBINARY","text");
		typeVariable.put("LONGVARBINARY","text");
		typeVariable.put("BOOLEAN","text");
		return typeVariable;
	}
	
	/**
	 * write \t
	 * 
	 * @param n = number of \t
	 * @return string \t n times
	 */
	protected String wT(int n){
		String t = "";
		for (int i = 0; i < n; i++) {
			t += "\t";
		}
		return t;
	}

}
