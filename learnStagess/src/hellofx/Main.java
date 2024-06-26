package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {



    public static void main(String[] args) {
    	launch(args);
    	

    }
    
    @Override
    public void start(Stage stage) throws Exception{
    	//Stage stage = new Stage();
    	
    	
    	Group root = new Group();
    	Scene scene = new Scene(root,600,600,Color.LIGHTSKYBLUE);
    	
    	
    	Image icon = new Image("tiger.png");
    	stage.getIcons().add(icon);
    	
    	
    	stage.setTitle("Stage learning programme");
    	
    	
    	Text text =new Text();
    	text.setText("Khela hobe!!!!!");
    	text.setX(50);
    	text.setY(50);
    	text.setFont(Font.font("Verdana",50));
    	text.setFill(Color.LIMEGREEN);
    	
    	
    	Line line = new Line();
    	line.setStartX(200);
    	line.setStartY(200);
    	line.setEndX(400);
    	line.setEndY(400);
    	
    	Line line2 = new Line();
    	line2.setStartX(400);
    	line2.setStartY(200);
    	line2.setEndX(200);
    	line2.setEndY(400);
    	

    	root.getChildren().add(text);
    	root.getChildren().add(line);
    	root.getChildren().add(line2);
    	
    	stage.setScene(scene);
    	stage.show();
    }
}