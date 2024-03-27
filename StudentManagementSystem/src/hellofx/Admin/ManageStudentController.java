package hellofx.Admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageStudentController {

	private Stage stage;
	private Scene scene;

	public void switchAdminPage1(ActionEvent event) throws IOException {
		System.out.println("Switching to Admin page 1 page...");
		Parent root = FXMLLoader.load(getClass().getResource("AdminPage1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void addStudent(ActionEvent event) throws IOException {
		System.out.println("Switching to addStudent page...");
		Parent root = FXMLLoader.load(getClass().getResource("addStudent.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}