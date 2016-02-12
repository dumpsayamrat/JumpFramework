package jumpframework.createproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQL {

	private Connection connect = null;

	public MySQL(String connection, String user, String pass) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection(connection + "?user=" + user + "&password=" + pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean getConnection() {
		if (connect != null)
			return true;
		else
			return false;
	}

	public void close() {
		// Close
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getTablesName(){
		ArrayList<String> tmp = new ArrayList<String>();
		
		try{
			DatabaseMetaData md = connect.getMetaData();
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
			DatabaseMetaData md = connect.getMetaData();
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
