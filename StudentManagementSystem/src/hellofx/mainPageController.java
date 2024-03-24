package hellofx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainPageController{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchAdminLogin(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public void switchStudentLogin(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchTeacherLogin(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("TeacherLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchmainPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	
	
}