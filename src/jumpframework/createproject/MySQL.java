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

	public MySQL(String connection, String user, String pass, String database) {

		try {
			if(database.equals("mysql")){
				Class.forName("com.mysql.jdbc.Driver");
			}else{
				Class.forName("org.postgresql.Driver");				
			}
			connect = DriverManager
					.getConnection(connection + "?user=" + user + "&password=" + pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Connection getConnection() {
		if (connect != null)
			return connect;
		else
			return null;
	}

	public boolean getIsConnection() {
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
			ResultSet rs = md.getTables(null, null, "%", new String[] { "TABLE" });
			while (rs.next()) {
				tmp.add(rs.getString(3));
				//System.out.println(rs.getString(3));
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
	 * 									,field[i][3] = is_primary, fields[i][4] = is_foreign, fields[i][5] = foreignField
	 * 									,field[i][6] = foreignPrimary
	 */
	public String[][] getFields(String tableName){
		ArrayList<String> tmpField = new ArrayList<String>();
		String[][] fields = null; 
		try{
			DatabaseMetaData md = connect.getMetaData();
			ResultSet rs = md.getColumns(null, null, tableName, null);
			
			
			while (rs.next()) {
				ResultSet rsPrimary = md.getPrimaryKeys(null, null, tableName);
				ResultSet rsImported = md.getImportedKeys(null, null, tableName);
				String name = rs.getString(4);
				String type = getDataType().get(Integer.parseInt(rs.getString(5)));
				String is_auto = rs.getString(23);
				String is_primary = "NO";
				String is_foreign = "NO";
				String foreignField = "NO";
				String foreignPrimary = "NO";
				while(rsPrimary.next()){
					if(name.equals(rsPrimary.getString(4))){
						is_primary = "YES";
					}
				}
				
				
				
				while(rsImported.next()){				

					ResultSet rsForeignColumn = md.getColumns(null, null, rsImported.getString(3), null);
					ResultSet rsForeignPrimary = md.getPrimaryKeys(null, null, rsImported.getString(3));
					if(name.equals(rsImported.getString(8))){
						int j=0;
						while(rsForeignColumn.next()&&j<2){
							foreignField = rsForeignColumn.getString(4);
							j++;
						}
						while(rsForeignPrimary.next()){
							foreignPrimary = rsForeignPrimary.getString(4);
						}
						is_foreign = rsImported.getString(3);
					}
				}
				
				tmpField.add(name+","
							+type+","
							+is_auto+","
							+is_primary+","
							+is_foreign+","
							+foreignField+","
							+foreignPrimary);
			}
			
			fields = new String[tmpField.size()][7];
			for(int i=0; i<fields.length; i++){	
				String[] tmp = tmpField.get(i).split(",");
				fields[i][0] = tmp[0];
				fields[i][1] = tmp[1];
				fields[i][2] = tmp[2];
				fields[i][3] = tmp[3];
				fields[i][4] = tmp[4];
				fields[i][5] = tmp[5];
				fields[i][6] = tmp[6];
			}
			
		}catch (SQLException e) {
			//System.out.println(e.getMessage());
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
