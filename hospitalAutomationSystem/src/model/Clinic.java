package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DbConnection;
import helper.DbPostgreConnection;

public class Clinic {

	private int id;
	private String name;

	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DbConnection dbConnection = new DbPostgreConnection();
	Connection connection = null;

	public Clinic() {
		super();
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public ArrayList<Clinic> getCiinicList() throws SQLException {
		Connection connection = dbConnection.connection();
		ArrayList<Clinic> clinics = new ArrayList<>();

		Clinic clinic;

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM clinics");
			while (resultSet.next()) {
				clinic = new Clinic();
				clinic.setId(resultSet.getInt("id"));
				clinic.setName(resultSet.getString("name"));
				clinics.add(clinic);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
			resultSet.close();
			connection.close();
		}

		return clinics;
	}

	public boolean addClinic(String name) throws SQLException {

		Connection connection = dbConnection.connection();
		statement = connection.createStatement();
		String query = "INSERT INTO " + "clinics(name) " + "VALUES(?);";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);

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

	public boolean deleteClinic(int id) throws SQLException {
		Connection connection = dbConnection.connection();
		System.out.println(id);

		String query = "DELETE FROM clinics WHERE id = ?";

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

	public boolean updateClinic(int id, String name) throws SQLException {
		Connection connection = dbConnection.connection();
		System.out.println(id);
		connection = dbConnection.connection();
		String query = "UPDATE clinics SET name = ? WHERE id = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}

		return true;

	}

	public Clinic getFetch(int id) {

		connection = dbConnection.connection();
		Clinic clinic = new Clinic();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM clinics WHERE id = " + id);
			while (resultSet.next()) {
				clinic.setId(resultSet.getInt("id"));
				clinic.setName(resultSet.getString("name"));
				break;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return clinic;

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

}
