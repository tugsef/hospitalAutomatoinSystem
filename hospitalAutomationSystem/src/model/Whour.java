package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DbConnection;
import helper.DbPostgreConnection;

public class Whour {
	private int id;
	private int doctor_id;
	private String doctor_name;
	private String wdate;
	private String statu;

	DbConnection dbConnection = new DbPostgreConnection();
	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public Whour() {
	}

	public Whour(int id, int doctor_id, String doctor_name, String wdate, String statu) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
		this.statu = statu;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

}
