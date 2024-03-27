package hellofx.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addStudentController {

	@FXML
	private Button buttCreate;

	@FXML
	private TextField txtBatch;

	@FXML
	private TextField txtDepertment;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtNane;

	@FXML
	private TextField txtProgramme;

	@FXML
	private TextField txtPassword;

	String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6693766";
	String username = "sql6693766";
	String password = "h1Pcz9vaTK";
	PreparedStatement pst;
	PreparedStatement pst2;
	Connection con;
	ResultSet rs;

	@FXML
	void addStudent(ActionEvent event) {
		try {
			String id = txtID.getText();
			String name = txtNane.getText();
			String department = txtDepertment.getText();
			String programme = txtProgramme.getText();
			String batch = txtBatch.getText();
			String pass = txtPassword.getText();

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver loaded successfully!!!");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}

			try {
				con = DriverManager.getConnection(url, username, password);
				System.out.println("Connection Established Successfully");

				pst = con.prepareStatement(
						"INSERT INTO studentInformation (id, name, depertment, programme, batch) VALUES (?, ?, ?, ?, ?)");
				pst.setString(1, id);
				pst.setString(2, name);
				pst.setString(3, department);
				pst.setString(4, programme);
				pst.setString(5, batch);

				pst2 = con.prepareStatement(
						"INSERT INTO studentLoginTable (student_id, name, password) VALUES (?, ?, ?)");
				pst2.setString(1, id);
				pst2.setString(2, name);
				pst2.setString(3, pass);

				System.out.println(pst);
				System.out.println(pst2);
				System.out.println("Executing update...");

				int rowsAffected = pst.executeUpdate(); // Use executeUpdate() for INSERT, UPDATE, DELETE
				int rowsAffected2 = pst2.executeUpdate();
				if (rowsAffected > 0 && rowsAffected2 > 0) {
					System.out.println("Added successfully");
				} else {
					System.out.println("Failed");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("SQL Exception occurred: " + e1.getMessage());
				// Show an error message or take appropriate action for SQL exception
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print the stack trace of the underlying exception
			System.out.println("Exception occurred: " + e.getMessage());
			// Show an error message or take appropriate action for other exceptions
		}
	}

}
