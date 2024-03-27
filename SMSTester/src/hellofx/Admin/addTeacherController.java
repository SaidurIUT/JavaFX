package hellofx.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addTeacherController {

	@FXML
	private Button buttCreate;

	@FXML
	private TextField txtDepertment;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtNane;

	@FXML
	private TextField txtPassword;

	private Stage stage;
	private Scene scene;

	String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6693766";
	String username = "sql6693766";
	String password = "h1Pcz9vaTK";
	PreparedStatement pst;
	PreparedStatement pst2;
	Connection con;
	ResultSet rs;

	@FXML
	void addTeacher(ActionEvent event) {
		try {
			String id = txtID.getText();
			String name = txtNane.getText();
			String department = txtDepertment.getText();
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

				pst = con.prepareStatement("INSERT INTO teacherInformation (id, name, depertment) VALUES (?, ?, ?)");
				pst.setString(1, id);
				pst.setString(2, name);
				pst.setString(3, department);

				pst2 = con.prepareStatement(
						"INSERT INTO teacherLoginTable (teacher_id, name, password) VALUES (?, ?, ?)");
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
					addedData();
					txtID.setText("");
					txtNane.setText("");
					txtDepertment.setText("");
					txtPassword.setText("");
					txtID.requestFocus();

				} else {
					System.out.println("Insertion Failed");
					invalidData();
				}

			} catch (SQLException e1) {
				invalidData();
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

	public void switchManageTeacherPage(ActionEvent event) throws IOException {
		System.out.println("Switching to Manage Student page...");
		Parent root = FXMLLoader.load(getClass().getResource("ManageTeacher.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	void invalidData() throws IOException {
		// Load the new FXML file
		Parent root = FXMLLoader.load(getClass().getResource("./popupMessage/invalidData.fxml"));

		// Create a new stage
		Stage stage = new Stage();

		// Create a new scene with the loaded FXML content
		Scene scene = new Scene(root);

		// Set the scene on the stage and show it
		stage.setScene(scene);
		stage.show();
	}

	void addedData() throws IOException {
		// Load the new FXML file
		Parent root = FXMLLoader.load(getClass().getResource("./popupMessage/addedData.fxml"));

		// Create a new stage
		Stage stage = new Stage();

		// Create a new scene with the loaded FXML content
		Scene scene = new Scene(root);

		// Set the scene on the stage and show it
		stage.setScene(scene);
		stage.show();
	}

}
