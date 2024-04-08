package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	public static Connection connectDB() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/hospital";
			String username = "root";
			String password = "123";

			Connection connect = DriverManager.getConnection(url, username, password);

			return connect;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}