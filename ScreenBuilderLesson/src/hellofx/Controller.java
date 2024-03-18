package hellofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class Controller {

//    @FXML
//    private Label label;
//
//    public void initialize() {
//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
//    }
	
	@FXML
	
	private Circle myCircle;
	private double x;
	private double y;
	
	
    
    public void up(ActionEvent e) {
    	//System.out.println("UP");
    	myCircle.setCenterY(y=y-10);
    }
    public void down(ActionEvent e) {
    	//System.out.println("DOWN");
    	myCircle.setCenterY(y=y+10);
    }
    public void right(ActionEvent e) {
    	//System.out.println("Right");
    	myCircle.setCenterX(x=x+10);
    }
    public void left(ActionEvent e) {
    	//System.out.println("Left");
    	myCircle.setCenterX(x=x-10);
    }
}