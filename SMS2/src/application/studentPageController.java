package application;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class studentPageController implements Initializable {

	@FXML
	private CheckBox login_checkBox;

	@FXML
	private AnchorPane login_form;

	@FXML
	private Button login_loginBtn;

	@FXML
	private PasswordField login_password;

	@FXML
	private TextField login_studentID;

	@FXML
	private TextField login_showPassword;

	@FXML
	private ComboBox<?> login_user;

	@FXML
	private AnchorPane main_form;

	private Connection connect;
	private PreparedStatement prepare;
	private ResultSet result;

	private AlertMessage alert = new AlertMessage();

	@FXML
	void loginAccount(ActionEvent event) {

		if (login_studentID.getText().isEmpty() || login_password.getText().isEmpty()) {
			alert.errorMessage("Incorrect student ID/Password");
		} else {

			String sql = "SELECT * FROM student WHERE student_id = ? AND password = ? AND date_delete IS NULL";
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
				String checkStatus = "SELECT status FROM student WHERE student_id = '" + login_studentID.getText()
						+ "' AND password = '" + login_password.getText() + "' AND status = 'Confirm'";

				prepare = connect.prepareStatement(checkStatus);
				result = prepare.executeQuery();

				if (result.next()) {

					if (!login_showPassword.isVisible()) {
						if (!login_showPassword.getText().equals(login_password.getText())) {
							login_showPassword.setText(login_password.getText());
						}
					} else {
						if (!login_showPassword.getText().equals(login_password.getText())) {
							login_password.setText(login_showPassword.getText());
						}
					}

					alert.errorMessage("Need the confimation of the Admin!");
				} else {
					prepare = connect.prepareStatement(sql);
					prepare.setString(1, login_studentID.getText());
					prepare.setString(2, login_password.getText());

					result = prepare.executeQuery();

					if (result.next()) {
//                         Data.student_id = Integer.parseInt(login_studentID.getText());
//                         
						alert.successMessage("Login Successfully!");
//                         
//                         Parent root = FXMLLoader.load(getClass().getResource("studentMainForm.fxml"));
//                         Stage stage = new Stage();
//
//                         stage.setScene(new Scene(root));
//                         stage.show();
//
//                         login_loginBtn.getScene().getWindow().hide();
					} else {
						alert.errorMessage("Incorrect student ID/Password");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

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

	public void userList() {
		List<String> listU = new ArrayList<>();

		for (String data : Users.users) {
			listU.add(data);
		}

		ObservableList listData = FXCollections.observableList(listU);
		login_user.setItems(listData);

	}

	@FXML
	void switchPage(ActionEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userList();
	}
}
