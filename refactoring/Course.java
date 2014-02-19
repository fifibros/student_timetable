package refactoring;

import java.sql.*;

public class Course {
	private int id;
	private String name;
	private int credits;
	private static DatabaseConnection db = new DatabaseConnection();
	private static Connection conn = null;

	public static Course create(int id, String name, int credits) throws Exception {
		db.connect();
		try {
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
		db.connect();
		try {
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
		db.connect();
		try {
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

	public Course(int id, String name, int credits) {
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