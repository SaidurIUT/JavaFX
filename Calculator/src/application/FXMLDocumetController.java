package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLDocumetController {

	@FXML
	private TextField txtDisplay;
	private int decimalClick = 0;
	private String generalOperationObject;
	private Double firstDouble;

	@FXML
	void handlerDecemalAction(ActionEvent event) {
		if (decimalClick == 0) {
			String decimalObject = ((Button) event.getSource()).getText();
			String oldText = txtDisplay.getText();
			String newText = oldText + decimalObject;
			txtDisplay.setText(newText);
			decimalClick = 1;
		}
	}

	@FXML
	void handlerDigitAction(ActionEvent event) {
		String digitObject = ((Button) event.getSource()).getText();
		String oldText = txtDisplay.getText();
		String newText = oldText + digitObject;
		txtDisplay.setText(newText);
	}

	@FXML
	void handlerEqualAction(ActionEvent event) {
		double secondDouble;
		double result = 0;
		String secondText = txtDisplay.getText();
		secondDouble = Double.parseDouble(secondText);

		switch (generalOperationObject) {

		case "+":
			result = firstDouble + secondDouble;
			break;

		case "-":
			result = firstDouble - secondDouble;
			break;

		case "*":
			result = firstDouble * secondDouble;
			break;

		case "/":
			result = firstDouble / secondDouble;
			break;

		default:

		}

		String format = String.format("%.5f", result);
		txtDisplay.setText(format);

	}

	@FXML
	void handlerGeneralAction(ActionEvent event) {
		generalOperationObject = ((Button) event.getSource()).getText();
		switch (generalOperationObject) {

		case "AC":
			txtDisplay.setText("");
			decimalClick = 0;
			break;

		case "+/-":
			double plusMinus = Double.parseDouble(String.valueOf(txtDisplay.getText()));
			plusMinus = plusMinus * (-1);
			txtDisplay.setText(String.valueOf(plusMinus));
			break;

		case "+":
		case "-":
		case "*":
		case "/":
		case "%":
			String currentText = txtDisplay.getText();
			firstDouble = Double.parseDouble(currentText);
			txtDisplay.setText("");
			decimalClick = 0;

		default:

		}

	}

}
