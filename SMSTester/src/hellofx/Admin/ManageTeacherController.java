package hellofx.Admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageTeacherController {

	private Stage stage;
	private Scene scene;

	public void switchAdminPage1(ActionEvent event) throws IOException {
		System.out.println("Switching to Admin Page 1 page...");
		Parent root = FXMLLoader.load(getClass().getResource("AdminPage1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void switchaddTeacher(ActionEvent event) throws IOException {
		System.out.println("Switching to addTeacher page...");
		Parent root = FXMLLoader.load(getClass().getResource("addTeacher.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
}