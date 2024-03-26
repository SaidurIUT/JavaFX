package hellofx.Teacher;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TeacherPage1Controller{
	
	private Stage stage;
	private Scene scene;
	
	
	@FXML
	public void logOut(ActionEvent event) throws IOException {
		System.out.println("Log-out from teacher ...");
		Parent root = FXMLLoader.load(getClass().getResource("../TeacherLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
		
}