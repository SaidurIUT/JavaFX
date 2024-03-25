package hellofx;

import java.awt.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class mainPageController{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchAdminLogin(ActionEvent event) throws IOException {
		System.out.println("Switching to Admin login page...");
		Parent root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public void switchStudentLogin(ActionEvent event) throws IOException {
		System.out.println("Switching to Student login page...");
		Parent root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchTeacherLogin(ActionEvent event) throws IOException {
		System.out.println("Switching to Teacher login page...");
		Parent root = FXMLLoader.load(getClass().getResource("TeacherLogin.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public void switchmainPage(ActionEvent event) throws IOException {
		System.out.println("Switching to Main page...");
		Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
		
}