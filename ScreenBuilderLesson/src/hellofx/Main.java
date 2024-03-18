package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
    	try {
        
    		Parent root = FXMLLoader.load(getClass().getResource("hellofx.fxml"));
    		Scene scene =new Scene(root);
    		primaryStage.setScene(scene);
    		primaryStage.show();
    		
    		
//    		primaryStage.setTitle("Hello World");
//    		primaryStage.setScene(new Scene(root, 400, 300));
//    		primaryStage.show();
    	} catch(Exception e) {
    		e.printStackTrace();    	
    		}
    }
    public static void main(String[] args) {
        launch(args);
    }
}