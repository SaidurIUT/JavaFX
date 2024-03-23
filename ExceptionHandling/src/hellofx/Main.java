package hellofx;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int dividend = sc.nextInt();
      int divisor = sc.nextInt();
      try {
    	  int result = dividend/divisor;
          System.out.println("Your Result is : "+ result);
      }catch(ArithmeticException e) {
    	  System.out.println(e.getMessage());
      }
      
    }   
}