package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private Connection connect = null;

	public MySQL(String db, String user, String pass) {

		try {
			Class.forName("com.mysql.jdbc");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pass);
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

}
