package hellofx;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import javafx.application.Platform;

public class StudentLoginController {

    @FXML
    private Label label;
    @FXML
    private TextField txtStudentID;
    @FXML
    private PasswordField txtStudentPass;
    @FXML
    private Button buttStudent;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    String url = "jdbc:mysql://sql6.freesqldatabase.com:3306/sql6693766";
    String username = "sql6693766";
    String password = "h1Pcz9vaTK";
    PreparedStatement pst;
    Connection con;
    ResultSet rs;

    @FXML
    void StudentLogin(ActionEvent event) {
        try {
            String UID = txtStudentID.getText();
            String pass = txtStudentPass.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded successfully!!!");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("Connection Established Successfully");
                pst = con.prepareStatement("select * from studentLoginTable where student_id = ? and password = ?; ");
                pst.setString(1, UID);
                pst.setString(2, pass);
                System.out.println(pst);
                System.out.println("Executing query...");
                rs = pst.executeQuery();

                if (rs.next()) {
                    System.out.println("Login successful");
                    switchStudentPage1();
                    //JOptionPane.showMessageDialog(null, "Log-In Success!!!");
                    
                    
                } else {
                    System.out.println("Login Failed");
                    //JOptionPane.showMessageDialog(null, "Log-In Failed!!!");
                   
                    txtStudentID.setText("");
                    txtStudentPass.setText("");
                    txtStudentID.requestFocus();
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

    @FXML
    void switchmainPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    void switchStudentPage1() throws IOException {
        // Load the new FXML file
        Parent root = FXMLLoader.load(getClass().getResource("./Student/StudentPage1.fxml"));
        
        // Create a new stage
        Stage stage = new Stage();
        
        // Create a new scene with the loaded FXML content
        Scene scene = new Scene(root);
        
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }

}


