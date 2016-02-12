package jumpframework.createproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseConnector {

	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL;

	// Database credentials
	static String USER = "username";
	static String PASS = "password";
	
	private Connection conn = null;

	public DatabaseConnector(String database, String username, String password) {
		DB_URL = "jdbc:mysql://localhost/"+database;
		USER = username;
		PASS = password;
	}

	public void connection() {
		
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			
		} // end try
	}
	
	/**
	 * Close Connection object's database
	 */
	public void close(){
		
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}
	
	
	
	public String[] getTablesName(){
		ArrayList<String> tmp = new ArrayList<String>();
		
		try{
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				tmp.add(rs.getString(3));
			}
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
		return tmp.toArray(new String[tmp.size()]);		
	}
	
	/**
	 * field data
	 * @param tableName
	 * @return fields  fields[i][0] = field's name, fields[i][1] = field's type, fields[i][2] = is_autoincrement
	 */
	public String[][] getFields(String tableName){
		ArrayList<String> tmpField = new ArrayList<String>();
		String[][] fields = null; 
		try{
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, tableName, null);
			while (rs.next()) {
				tmpField.add(rs.getString(4)+","
							+getDataType().get(Integer.parseInt(rs.getString(5)))+","
							+rs.getString(23));
				
			}
			fields = new String[tmpField.size()][3];
			for(int i=0; i<fields.length; i++){	
				String[] tmp = tmpField.get(i).split(",");
				fields[i][0] = tmp[0];
				fields[i][1] = tmp[1];
				fields[i][2] = tmp[2];
			}
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
            e.printStackTrace();
        }
		
		return fields;
		
	}
	
	/**
	 *  get SQL type from java.sql.Types
	 * get data type from java.sql.Types's code
	 */
	private static HashMap<Integer, String> getDataType(){
		HashMap<Integer, String> dataType = new HashMap<Integer, String>();		
		dataType.put(-7, "BIT");
		dataType.put(-6, "TINYINT");
		dataType.put(5, "SMALLINT");
		dataType.put(4, "INTEGER");
		dataType.put(-5, "BIGINT");
		dataType.put(6, "FLOAT");
		dataType.put(7, "REAL");
		dataType.put(8, "DOUBLE");
		dataType.put(2, "NUMERIC");
		dataType.put(3, "DECIMAL");
		dataType.put(1, "CHAR");
		dataType.put(12, "VARCHAR");
		dataType.put(-1, "LONGVARCHAR");
		dataType.put(91, "DATE");
		dataType.put(92, "TIME");
		dataType.put(93, "TIMESTAMP");
		dataType.put(-2, "BINARY");
		dataType.put(-3, "VARBINARY");
		dataType.put(-4, "LONGVARBINARY");
		dataType.put(16, "BOOLEAN");
		return dataType;
	}

}
