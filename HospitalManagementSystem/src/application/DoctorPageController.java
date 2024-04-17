package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorPageController implements Initializable {

	@FXML
	private CheckBox login_checkBox;

	@FXML
	private TextField login_doctorID;

	@FXML
	private AnchorPane login_form;

	@FXML
	private Button login_loginBtn;

	@FXML
	private PasswordField login_password;

	@FXML
	private Hyperlink login_registerHere;

	@FXML
	private TextField login_showPassword;

	@FXML
	private ComboBox<?> login_user;

	@FXML
	private AnchorPane main_form;

	@FXML
	private CheckBox register_checkBox;

	@FXML
	private TextField register_email;

	@FXML
	private AnchorPane register_form;

	@FXML
	private TextField register_fullName;

	@FXML
	private TextField register_doctorID;

	@FXML
	private Hyperlink register_loginHere;

	@FXML
	private PasswordField register_password;

	@FXML
	private TextField register_showPassword;

	@FXML
	private Button register_signupBtn;

	@FXML
	private TextField resister_doctorID;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	private final AlertMessage alert = new AlertMessage();

	@FXML
	void loginShowPassword(ActionEvent event) {

		if (login_checkBox.isSelected()) {
			login_showPassword.setText(login_password.getText());
			login_password.setVisible(false);
			login_showPassword.setVisible(true);
		} else {
			login_password.setText(login_showPassword.getText());
			login_password.setVisible(true);
			login_showPassword.setVisible(false);
		}

	}

	@FXML
	void registerAccount(ActionEvent event) {
		if (register_fullName.getText().isEmpty() || register_email.getText().isEmpty()
				|| register_doctorID.getText().isEmpty() || register_password.getText().isEmpty()) {
			alert.errorMessage("Please fill all blank fields");
		} else {

			String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = '" + register_doctorID.getText() + "'"; // LETS
																													// CREATE
																													// OUR
																													// TABLE
																													// FOR
																													// DOCTOR
																													// FIRST

			connect = Database.connectDB();

			try {

				if (!register_showPassword.isVisible()) {
					if (!register_showPassword.getText().equals(register_password.getText())) {
						register_showPassword.setText(register_password.getText());
					}
				} else {
					if (!register_showPassword.getText().equals(register_password.getText())) {
						register_password.setText(register_showPassword.getText());
					}
				}

				prepare = connect.prepareStatement(checkDoctorID);
				result = prepare.executeQuery();

				if (result.next()) {
					alert.errorMessage(register_doctorID.getText() + " is already taken");
				} else if (register_password.getText().length() < 8) {
					alert.errorMessage("Invalid password, at least 8 characters needed");
				} else {

					String insertData = "INSERT INTO doctor (full_name, email, doctor_id, password, date, status) "
							+ "VALUES(?,?,?,?,?,?)";

					prepare = connect.prepareStatement(insertData);

					Date date = new Date(0);
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());

					prepare.setString(1, register_fullName.getText());
					prepare.setString(2, register_email.getText());
					prepare.setString(3, register_doctorID.getText());
					prepare.setString(4, register_password.getText());
					prepare.setString(5, String.valueOf(sqlDate));
					prepare.setString(6, "Confirm");

					prepare.executeUpdate();

					alert.successMessage("Registered Succesfully!");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	void registerShowPassword(ActionEvent event) {

	}

	public void userList() {
		List<String> listU = new ArrayList<>();

		for (String data : Users.users) {
			listU.add(data);
		}

		ObservableList listData = FXCollections.observableList(listU);
		login_user.setItems(listData);

	}

	public void switchPage() {

		if (login_user.getSelectionModel().getSelectedItem() == "Admin Portal") {

			try {

				Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Hospital Management System");

				stage.setMinWidth(340);
				stage.setMinHeight(580);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {

			try {

				System.out.println("Doctor Portal Clicked");

				Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Hospital Management System");

				stage.setMinWidth(340);
				stage.setMinHeight(580);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {

			try {

				Parent root = FXMLLoader.load(getClass().getResource("PatientPage.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Hospital Management System");

				stage.setMinWidth(340);
				stage.setMinHeight(580);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		login_user.getScene().getWindow().hide();

	}

	@FXML
	void switchFrom(ActionEvent event) {

		if (event.getSource() == register_loginHere) {
			login_form.setVisible(true);
			register_form.setVisible(false);
		} else if (event.getSource() == login_registerHere) {
			login_form.setVisible(false);
			register_form.setVisible(true);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
