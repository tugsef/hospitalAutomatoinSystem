package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.Config;

public class Doctor extends User {

	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public boolean addWhour(int doctor_id, String doctor_name, String wdate) throws SQLException {
		connection = dbConnection.connection();

		int key = 0;
		int count = 0;

		String query = "INSERT INTO whour " + "(doctor_id , doctor_name , wdate) VALUES" + "(? , ? , ?)";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM whour WHERE statu = 'active' AND doctor_id = " + doctor_id
					+ " AND wdate = '" + wdate + "'");

			while (resultSet.next()) {
				count++;
				break;
			}
			if (count == 0) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();

			} else {
				Config.showMsg("Kayıt Mevcut");
			}
			key = 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
public ArrayList<User> getDoctorList() throws SQLException  {
	
		
		connection =dbConnection.connection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<User> doctors = new ArrayList<>();
		User user = null;
		 try {
			statement = connection.createStatement();
			 resultSet = statement.executeQuery("SELECT * FROM users WHERE person = 'doctor'");
			 while (resultSet.next()) {
					user = new User(resultSet.getInt("id"), resultSet.getString("tcno"),resultSet.getString("password")
							, resultSet.getString("name"), resultSet.getString("person"));
					doctors.add(user);
				}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
			statement.close();
			resultSet.close();
			
		}
		
		 
		 
		
		 return doctors;
	}
public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {

	connection = dbConnection.connection();
	Statement statement = null;
	ResultSet resultSet = null;
	ArrayList<Whour> whours = new ArrayList<>();
	Whour whour = null;
	try {
		statement = connection.createStatement();
		resultSet = statement
				.executeQuery("SELECT * FROM whour WHERE statu = 'active' AND doctor_id = " + doctor_id);
		while (resultSet.next()) {
			whour = new Whour();
			whour.setId(resultSet.getInt("id"));
			whour.setDoctor_id(resultSet.getInt("doctor_id"));
			whour.setDoctor_name(resultSet.getString("doctor_name"));
			whour.setStatu(resultSet.getString("statu"));
			whour.setWdate(resultSet.getString("wdate"));
			whours.add(whour);
			
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		connection.close();
		statement.close();
		resultSet.close();

	}

	return whours;
}
public boolean deleteWhour(int id) throws SQLException  {
	
	System.out.println(id);
	connection = dbConnection.connection();
	
	String query = "DELETE FROM whour WHERE id = ?";
	
	boolean key = false;
	
	try {
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		
		preparedStatement.executeUpdate();
		
		key =true;
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
		
	} 
	if (key) 
		return true;
		else 
			return false;
			
		
	
	
	
	
}
}
