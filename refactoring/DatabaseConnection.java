package refactoring;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/";
	private String db = "jdbc:odbc:Registration";
	private String driver = "com.mysql.jdbc.Driver";
	private String username = "root";
	private String password = "";

	public Connection connect() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url + db, username, password);
			if (connection == null) {
				System.out.println("Connection cannot be established");
			}
			return connection;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
