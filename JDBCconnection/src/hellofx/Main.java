import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		String sql = "select name from adminLoginTable where id = 101";
		String url = "jdbc:mysql://localhost:3306/college";
		String username = "root";
		String password = "123";

		try (Connection con = DriverManager.getConnection(url, username, password);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			if (rs.next()) {
				String name = rs.getString("name");
				System.out.println("Name: " + name);
			} else {
				System.out.println("Admin with ID 101 not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
