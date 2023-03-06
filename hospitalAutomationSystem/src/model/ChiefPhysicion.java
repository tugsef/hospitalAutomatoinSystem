package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.Config;

public class ChiefPhysicion extends User {

	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public ChiefPhysicion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiefPhysicion(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		connection = dbConnection.connection();
		Statement statement = connection.createStatement();
		String query = "INSERT INTO " + "users(tcno , password , person , name) " + "VALUES(?,? ,?::person,?);";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, "doctor");
			preparedStatement.setString(4, name);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			connection.close();
			statement.close();
			preparedStatement.close();
		}

		return true;

	}

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		connection = dbConnection.connection();
		Statement statement = connection.createStatement();

		String query = "INSERT INTO " + "worker(user_id , clinic_id) " + "VALUES( ? , ?);";
		preparedStatement = connection.prepareStatement(query);

		try {
			resultSet = statement
					.executeQuery("SELECT * FROM worker WHERE user_id = " + user_id + " AND clinic_id = " + clinic_id);
			int count = 0;

			while (resultSet.next()) {
				count++;
			}

			if (count == 0) {

				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);

				preparedStatement.executeUpdate();
			} else {
				Config.showMsg("Kayıt Mevcut...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			connection.close();
			statement.close();
			preparedStatement.close();
		}

		return true;

	}

	public boolean deleteDoctor(int id) throws SQLException {

		System.out.println(id);
		connection = dbConnection.connection();

		String query = "DELETE FROM users WHERE id = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		return true;

	}

	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException {

		System.out.println(id);
		connection = dbConnection.connection();
		Statement statement = connection.createStatement();
		String query = "UPDATE users SET tcno = ? , password = ? , name = ? WHERE id = ?";

		boolean key = false;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setInt(4, id);

			preparedStatement.executeUpdate();
			key = true;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			preparedStatement.close();
			statement.close();
			connection.close();
		}

		if (key)
			return true;
		else
			return false;

	}

	public ArrayList<User> getDoctorList() throws SQLException {

		connection = dbConnection.connection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<User> doctors = new ArrayList<>();
		User user = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users WHERE person = 'doctor'");
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("tcno"), resultSet.getString("password"),
						resultSet.getString("name"), resultSet.getString("person"));
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

	public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException {

		connection = dbConnection.connection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<User> doctors = new ArrayList<>();
		User user = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"SELECT u.id , u.tcNo , u.person , u.password , u.name FROM worker w LEFT JOIN users u ON w.user_id = u.id WHERE clinic_id = "
							+ clinic_id);
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("tcno"), resultSet.getString("password"),
						resultSet.getString("name"), resultSet.getString("person"));
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

}
