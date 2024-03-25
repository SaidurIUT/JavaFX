package hellofx.Admin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminPage1Controller{
	
	private Stage stage;
	private Scene scene;
	
	@FXML
	public void manageStudent(ActionEvent event) throws IOException {
		System.out.println("Switching to Manage Student page...");
		Parent root = FXMLLoader.load(getClass().getResource("ManageStudent.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@FXML
	public void manageTeacher(ActionEvent event) throws IOException {
		System.out.println("Switching to Manage Teacher page...");
		Parent root = FXMLLoader.load(getClass().getResource("ManageTeacher.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
		
}