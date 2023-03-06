package helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbPostgreConnection implements DbConnection {
	
	private static final String url = "jdbc:postgresql://localhost/hospital";
	private static final String user = "postgres";
	private static final String password = "postgre";
	
	

	@Override
	public Connection connection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Bağlantı Başarılı...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()	);
		}
		
		
		return connection;
		
	}
	
	
}
