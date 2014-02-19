package refactoring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {

	private int id;
	private String name;
	private Schedule schedule;
	private Course course;
	private static DatabaseConnection db = new DatabaseConnection();
	private static Connection conn = null;

	public static Student create(int id, String name) throws Exception {
		db.connect();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM student WHERE name = '" + name + "';");
			statement.executeUpdate("INSERT INTO student VALUES ('" + name + "';'");
			return new Student(id, name);
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public static Student find(String id, String name) {
		db.connect();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM student WHERE id = '" + id + "';");
			if (!result.next()) return null;
			int newId = 1 + result.getInt(1);
			return new Student(newId, name);
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
			statement.executeUpdate("DELETE FROM STUDENT WHERE id = '" + id + "';");
			statement.executeUpdate("INSERT INTO student VALUES('" + id + "','");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}