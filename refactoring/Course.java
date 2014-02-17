package refactoring;

import java.sql.*;

public class Course {
	private int id;
	private String name;
	private int credits;
	static String url = "jdbc:odbc:Registration";
	static { 
		try { 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
		}
		catch (Exception ignored) {} 
	}

	public static Course create(int id, String name, int credits) throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "", "");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM course WHERE name = '" + name + "';");
			statement.executeUpdate("INSERT INTO course VALUES ('" + name + "', '" + credits + "');");
			return new Course(id, name, credits);
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Course find(String name) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "", "");
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM course WHERE Name = '" + name + "';");
			if (!result.next()) return null;
			int newId = 1 + result.getInt(1);
			int credits = result.getInt("Credits");
			return new Course(newId, name, credits);
		} 
		catch (Exception ex) {
			return null;
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public void update() throws Exception {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "", "");
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM COURSE WHERE name = '" + name + "';");
			statement.executeUpdate("INSERT INTO course VALUES('" + name + "','" + credits + "');");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	Course(int id, String name, int credits) {
		this.id = id;
		this.name = name;
		this.credits = credits;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCredits() {
		return credits;
	}

	public String getName() {
		return name;
	}

}