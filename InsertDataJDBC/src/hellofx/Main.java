package hellofx;

import javafx.application.Application;
import java.sql.*;
public class Main  {

    public static void main(String[] args) throws ClassNotFoundException{
    	
    	String url = "jdbc:mysql://localhost:3306/mydatabase";
    	String username="root";
    	String password="123";
    	String query = "insert into employess values (4,'Modna' ,'Gate man',9999.99);";
    	
        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	System.out.println("Driver loded successfully");
        }catch(ClassNotFoundException e){
        	System.out.println(e.getMessage());
        }
        
        try {
        	Connection con = DriverManager.getConnection(url,username, password);
        	System.out.println("Connection established successfully");
        	Statement stmt = con.createStatement();
        	int rowsAffected = stmt.executeUpdate(query);
        	
        	if(rowsAffected > 0 ) {
        		System.out.println("Insert Successfull!!! "+rowsAffected+ " row(s) affected." );
        	}else{
        		System.out.println("Insertion Failed!!!");
        	}
        	
        	
        	stmt.close();
        	con.close();
        	System.out.println();
        	System.out.println("Connection Colosed");
        	
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
        }
        
        
        
    }
}