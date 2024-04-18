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

public class teacherPageController implements Initializable {

	@FXML
	private CheckBox login_checkBox;

	@FXML
	private TextField login_teacherID;

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
	private TextField register_teacherID;

	@FXML
	private Hyperlink register_loginHere;

	@FXML
	private PasswordField register_password;

	@FXML
	private TextField register_showPassword;

	@FXML
	private Button register_signupBtn;

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
	void loginAccount() {

		if (login_teacherID.getText().isEmpty() || login_password.getText().isEmpty()) {
			alert.errorMessage("Incorrect Teacher ID/Password");
		} else {

			String sql = "SELECT * FROM teacher WHERE teacher_id = ? AND password = ? AND delete_date IS NULL";
			connect = Database.connectDB();

			try {

				if (!login_showPassword.isVisible()) {
					if (!login_showPassword.getText().equals(login_password.getText())) {
						login_showPassword.setText(login_password.getText());
					}
				} else {
					if (!login_showPassword.getText().equals(login_password.getText())) {
						login_password.setText(login_showPassword.getText());
					}
				}

				// CHECK IF THE STATUS OF THE teacher IS CONFIRM
				String checkStatus = "SELECT status FROM teacher WHERE teacher_id = '" + login_teacherID.getText()
						+ "' AND password = '" + login_password.getText() + "' AND status = 'Confirm'";

				prepare = connect.prepareStatement(checkStatus);
				result = prepare.executeQuery();

				if (result.next()) {

					alert.errorMessage("Need the confimation of the Admin!");
				} else {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, login_teacherID.getText());
					prepare.setString(2, login_password.getText());

					result = prepare.executeQuery();

					if (result.next()) {

//						Data.teacher_id = result.getString("teacher_id");
//						Data.teacher_name = result.getString("full_name");

						alert.successMessage("Login Successfully!");

//						Parent root = FXMLLoader.load(getClass().getResource("teacherMainForm.fxml"));
//						Stage stage = new Stage();
//
//						stage.setTitle("Student Management System | teacher Main Form");
//						stage.setScene(new Scene(root));
//						stage.show();

						// login_loginBtn.getScene().getWindow().hide();

					} else {
						alert.errorMessage("Incorrect teacher ID/Password");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	void registerAccount(ActionEvent event) {
		if (register_fullName.getText().isEmpty() || register_email.getText().isEmpty()
				|| register_teacherID.getText().isEmpty() || register_password.getText().isEmpty()) {
			alert.errorMessage("Please fill all blank fields");
		} else {

			String checkteacherID = "SELECT * FROM teacher WHERE teacher_id = '" + register_teacherID.getText() + "'";

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

				prepare = connect.prepareStatement(checkteacherID);
				result = prepare.executeQuery();

				if (result.next()) {
					alert.errorMessage(register_teacherID.getText() + " is already taken");
				} else if (register_password.getText().length() < 8) {
					alert.errorMessage("Invalid password, at least 8 characters needed");
				} else {

					String insertData = "INSERT INTO teacher (full_name, email, teacher_id, password, date, status) "
							+ "VALUES(?,?,?,?,?,?)";

					prepare = connect.prepareStatement(insertData);

					Date date = new Date(0);
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());

					prepare.setString(1, register_fullName.getText());
					prepare.setString(2, register_email.getText());
					prepare.setString(3, register_teacherID.getText());
					prepare.setString(4, register_password.getText());
					prepare.setString(5, String.valueOf(sqlDate));
					prepare.setString(6, "Confirm");

					System.out.println(prepare);

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

		if (register_checkBox.isSelected()) {
			register_showPassword.setText(register_password.getText());
			register_showPassword.setVisible(true);
			register_password.setVisible(false);
		} else {
			register_password.setText(register_showPassword.getText());
			register_showPassword.setVisible(false);
			register_password.setVisible(true);
		}
	}

	public void registerteacherID() {
		String teacherID = "TID-";
		int tempID = 0;
		String sql = "SELECT MAX(id) FROM teacher";

		connect = Database.connectDB();

		try {

			prepare = connect.prepareStatement(sql);
			result = prepare.executeQuery();

			if (result.next()) {
				tempID = result.getInt("MAX(id)");
			}

			if (tempID == 0) {
				tempID += 1;
				teacherID += tempID;
			} else {
				teacherID += (tempID + 1);
			}

			register_teacherID.setText(teacherID);
			register_teacherID.setDisable(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		if (login_user.getSelectionModel().getSelectedItem() == "admin Portal") {

			try {

				Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Student Management System");

				stage.setMinWidth(340);
				stage.setMinHeight(580);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (login_user.getSelectionModel().getSelectedItem() == "teacher Portal") {

			try {

				System.out.println("teacher Portal Clicked");

				Parent root = FXMLLoader.load(getClass().getResource("teacherPage.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Student Management System");

				stage.setMinWidth(340);
				stage.setMinHeight(580);

				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (login_user.getSelectionModel().getSelectedItem() == "student Portal") {

			try {

				Parent root = FXMLLoader.load(getClass().getResource("studentPage.fxml"));
				Stage stage = new Stage();

				stage.setTitle("Student Management System");

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
	public void initialize(URL location, ResourceBundle resources) {
		registerteacherID();
		userList();
	}

}
