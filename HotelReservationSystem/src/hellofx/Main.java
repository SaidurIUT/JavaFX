package hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.util.*;


public class Main {

    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String password="123";
    
    		

    public static void main(String[] args) throws ClassNotFoundException , SQLException{
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
        	System.out.println(e.getMessage());
        }
        
        try {
        	Connection connection = DriverManager.getConnection(url,username,password);
        	while(true) {
        		System.out.println();
        		System.out.println("---:::Hotel Management System:::---");
        		Scanner scanner = new Scanner(System.in);
        		System.out.println("1. Reserve a room");
        		System.out.println("2. View Reservation");
        		System.out.println("3. Get room number");
        		System.out.println("4. Update reservation");
        		System.out.println("5. Delete reservation");
        		System.out.println("0 Exit");
        		System.out.println("Chooser an option: ");
        		
        		int choise = scanner.nextInt();
        		
        		switch(choise) {
        		
        		case 1:
        			reserveRoom(connection,scanner);
        			break;
        		case 2:
        			viewReservation(connection);
        			break;
        		case 3: 
        			getRoomNumber(connection,scanner);
        			break;
        		case 4:
        			updateReservation(connection,scanner);
        			break;
        		case 5:
        			deleteReservation(connection,scanner);
        			break;
        		case 0: 
        			exit();
        			scanner.close();
        			return;
        			
        		default:
        			System.out.println("Invalid Choise ... Try again...");
        		}
        				
        	}
        	
        }catch(SQLException e) {
        	System.out.println(e.getMessage());
        }catch(InterruptedException e) {
        	throw new RuntimeException(e);
        }
    }
    
    private static void reserveRoom(Connection connection, Scanner scanner) {
    	try {
    		System.out.println("Enter guest name: ");
    		String guestName = scanner.next();
    		scanner.nextLine();
    		System.out.println("Enter room number: ");
    		int roomNumber = scanner.nextInt();
    		System.out.println("Enter contact number");
    		String contactNumber = scanner.next();
    		
    		String sql = "insert into reservations (guest_name,room_number,contact_no) " +
    					"values('" + guestName + "' ,"+ roomNumber + " , '" + contactNumber+"')";
    		try (Statement statement = connection.createStatement()){
    			int affectedRows = statement.executeUpdate(sql);
    			if(affectedRows > 0 ) {
    				System.out.println("Reservation Succerssfull!!!");
    			}else {
    				System.out.println("Reservation failed !!!");
    			}
    		}
    		
    		
    	}catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    
    
    private static void viewReservation(Connection connection) throws SQLException {
    	String sql = "select reservation_id,guest_name,room_number,contact_no,reservation_date from reservations;";
    	try(Statement statement = connection.createStatement(); 
    		ResultSet resultSet = statement.executeQuery(sql)){
    		
    		System.out.println();
    		System.out.println("Current reservation: ");
    		System.out.println();
    		System.out.println("+--------------+-----------------+-----------+--------------+-----------------------+");
    		System.out.println("|Reservation ID|     Guest       |Room Number|Contact Number|    Reservation Date   |");
    		System.out.println("+--------------+-----------------+-----------+--------------+-----------------------+");
    		
    		
    		while(resultSet.next()) {
    			int reservationID = resultSet.getInt("reservation_id");
    			String guestName = resultSet.getString("guest_name");
    			int roomNumber = resultSet.getInt("room_number");
    			String contactNumber = resultSet.getString("contact_no");
    			String reservationDate = resultSet.getTimestamp("reservation_date").toString();
    			
    			System.out.printf("| %-12d | %-15s | %-9d | %-12s | %-16s |\n",
    					reservationID,guestName,roomNumber,contactNumber,reservationDate);
    			System.out.println("+--------------+-----------------+-----------+--------------+-----------------------+");
    			
    			
    		}
    	}
    }
    
    private static void getRoomNumber(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter reservation ID: ");
            int reservationId = scanner.nextInt();
            System.out.print("Enter guest name: ");
            String guestName = scanner.next();

            String sql = "SELECT room_number FROM reservations " +
                    "WHERE reservation_id = " + reservationId +
                    " AND guest_name = '" + guestName + "'";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room number for Reservation ID " + reservationId +
                            " and Guest " + guestName + " is: " + roomNumber);
                } else {
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateReservation(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter reservation ID to update: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            System.out.print("Enter new guest name: ");
            String newGuestName = scanner.nextLine();
            System.out.print("Enter new room number: ");
            int newRoomNumber = scanner.nextInt();
            System.out.print("Enter new contact number: ");
            String newContactNumber = scanner.next();

            String sql = "UPDATE reservations SET guest_name = '" + newGuestName + "', " +
                    "room_number = " + newRoomNumber + ", " +
                    "contact_number = '" + newContactNumber + "' " +
                    "WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation updated successfully!");
                } else {
                    System.out.println("Reservation update failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteReservation(Connection connection, Scanner scanner) {
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = scanner.nextInt();

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully!");
                } else {
                    System.out.println("Reservation deletion failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection connection, int reservationId) {
        try {
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                return resultSet.next(); // If there's a result, the reservation exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle database errors as needed
        }
    }


    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(100);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }
 
}