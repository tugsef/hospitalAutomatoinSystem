package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helper.Config;

public class Patient extends User {

	Connection connection = dbConnection.connection();
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		// TODO Auto-generated constructor stub
	}

	public boolean addPatient(String tcno, String password, String name) throws SQLException {
		connection = dbConnection.connection();

		int key = 0;
		boolean duplicate = false;

		String query = "INSERT INTO users " + "(tcno , password , name) VALUES" + "(? , ? , ?)";

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users WHERE tcno = '" + tcno + "'");

			while (resultSet.next()) {
				duplicate = true;
				Config.showMsg("Kayıt Mevcut");
				break;
			}
			if (!duplicate) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
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

}
