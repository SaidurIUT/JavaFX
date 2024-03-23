import javax.xml.transform.Result;
import java.sql.*;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6693766";
		String username = "sql6693766";
		String password = "h1Pcz9vaTK";
		String query = "select * from employess where name = ? ";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loded successfully!!!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established Successfully");

			PreparedStatement prepareStatement = con.prepareStatement(query);
			prepareStatement.setString(1,"Saeed");
			ResultSet resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("ID: "+id);
				System.out.println("Name: "+name);
			}
			
			resultSet.close();
			prepareStatement.close();
			con.close();
			System.out.println();
			System.out.println("Connection closed successfully");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}


