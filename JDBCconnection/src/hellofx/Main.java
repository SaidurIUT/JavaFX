package hellofx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 400, 300));
//        primaryStage.show();
//        
//        
//        
//    }


    public static void main(String[] args) throws Exception {
        //launch(args);
    	
    	String sql = "select name from student where id = 2141132";
        String url="jdbc:mysql://localhost:3306/damo";
        String username = "root";
        String password = "123";
        
        
        Connection con = DriverManager.getConnection(url, username, password);
        
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(sql);
        
        rs.next();
        
		String name = rs.getString(1);
		
		System.out.println(name);
		
		con.close();
		
        
    }
}