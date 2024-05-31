package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class mainPageController {

	@FXML
	private Button longBreak;

	@FXML
	private AnchorPane myPan;

	@FXML
	private Button pomodoro;

	@FXML
	private Button shortBreak;

	@FXML
	private Button startBtn;

	@FXML
	private Button stopBtn;

	// this class change the pan color when user click on shortBreak button
	@FXML
	void shortBreakAction() {
		myPan.setStyle("-fx-background-color: #59b39e");
		pomodoro.setStyle("-fx-background-color: #59b39e");
		shortBreak.setStyle("-fx-background-color: #59b39e");
		longBreak.setStyle("-fx-background-color: #59b39e");

	}

	// this class change the pan color when user click on pomodoro button
	@FXML
	void pomodoroAction() {
		myPan.setStyle("-fx-background-color: #ba5465");
		pomodoro.setStyle("-fx-background-color: #ba5465");
		shortBreak.setStyle("-fx-background-color: #ba5465");
		longBreak.setStyle("-fx-background-color: #ba5465");
	}

	// this class change the pan color when user click on longBreak button
	@FXML
	void longBreakAction() {
		myPan.setStyle("-fx-background-color: #5992b3");
		pomodoro.setStyle("-fx-background-color: #5992b3");
		shortBreak.setStyle("-fx-background-color: #5992b3");
		longBreak.setStyle("-fx-background-color: #5992b3");
	}

	// then startBtn pressed the button will be disabled and stopBtn will be enabled
	@FXML
	void startAction() {
		System.out.println("start button pressed");
		startBtn.setDisable(true);
		stopBtn.setDisable(false);
	}

	// then stopBtn pressed the button will be disabled and startBtn will be enabled
	@FXML
	void stopAction() {
		System.out.println("stop button pressed");
		startBtn.setDisable(false);
		stopBtn.setDisable(true);
	}

	public void initialize() {
		myPan.setStyle("-fx-background-color: #ba5465");
		pomodoro.setStyle("-fx-background-color: #ba5465");
		shortBreak.setStyle("-fx-background-color: #ba5465");
		longBreak.setStyle("-fx-background-color: #ba5465");

	}

}
