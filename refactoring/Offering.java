package refactoring;

import java.sql.*;

public class Offering {
	private int id;
	private Course course;
	private String daysTimes;
	private String day;
	private String time;
	private static DatabaseConnection db = new DatabaseConnection();
	private static Connection conn = null;

	public static Offering create(Course course, String daysTimesString) throws Exception {
		db.connect();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM offering;");
			result.next();
			int newId = 1 + result.getInt(1);
			statement.executeUpdate("INSERT INTO offering VALUES ('"+ newId + "','" + course.getName() + "','" + daysTimesString + " " + "');");
			return new Offering(newId, course, daysTimesString);
		} 
			finally {
				try { 
					conn.close(); 
				} 
				catch (Exception ignored) {}
			}
	}

	public static Offering find(int id) {
		db.connect();
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM offering WHERE ID =" + id + ";");
			if (result.next() == false)
				return null;
			String courseName = result.getString("Name");
			Course course = Course.find(courseName);
			String dateTime = result.getString("Day");
			conn.close();
			return new Offering(id, course, dateTime);
		} 
		catch (Exception ex) {
			try { 
				conn.close(); 
			} catch (Exception ignored) {}
			return null;
		}
	}

	public void update() throws Exception {
		db.connect();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("DELETE FROM Offering WHERE ID =" + id + ";");
			statement.executeUpdate("INSERT INTO Offering VALUES('" + id + "','" + course.getName() + "','" + daysTimes + "');");
		} 
		finally {
			try { 
				conn.close(); 
			} 
			catch (Exception ignored) {}
		}
	}

	public Offering(int id, Course course, String daysTimesCsv) {
		this.id = id;
		this.course = course;
		this.daysTimes = daysTimesCsv;
		String[] daysTimesString = daysTimesCsv.split(",");
	}

	public int getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDaysTimes() {
		return daysTimes;
	}

	public void setDaysTimes(String daysTimes) {
		this.daysTimes = daysTimes;
	}

	public String toString() {
		return "Offering " + getId() + ": " + getCourse() + " meeting " + getDaysTimes();
	}

}