package jump.view.main;
import java.sql.*;
public class Connector {
		// JDBC driver name and database URL
		private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		private static String DB_URL = "jdbc:mysql://localhost/";

		// Database credentials
		static String USER = "username";
		static String PASS = "password";
		
		private Connection conn = null;

		public Connector(String database, String username, String password) {
			DB_URL += database;
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
		
		public void showTableName(){
			System.out.println("**********Show table name************");
			try{
				DatabaseMetaData md = conn.getMetaData();
				ResultSet rs = md.getTables(null, null, "%", null);
				while (rs.next()) {
					System.out.println(rs.getString(3));
				}
			}catch (SQLException e) {
	            e.printStackTrace();
	        }
		}

	}
	
	

