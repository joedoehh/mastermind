package joedoe.mastermind.application;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import joedoe.mastermind.core.Solver;
import joedoe.mastermind.types.Color;
import joedoe.mastermind.types.Row;

public class MasterMindController {

	private RowModel rowModel = new RowModel();

	@FXML
	private Circle firstColorCircle;

	@FXML
	private Circle secondColorCircle;

	@FXML
	private Circle thirdColorCircle;

	@FXML
	private Circle fourthColorCircle;

	@FXML
	private Button whiteButton;

	@FXML
	private Button greyButton;

	@FXML
	private Button orangeButton;

	@FXML
	private Button pinkButton;

	@FXML
	private Button redButton;

	@FXML
	private Button greenButton;

	@FXML
	private Button blueButton;

	@FXML
	private Button yellowButton;

	@FXML
	private Button solveButton;
	
	@FXML
	private TextArea solutionTextArea;

	@FXML
	protected void colorButtonPressed(ActionEvent event) {
		Button buttonPressed = (Button) event.getSource();
		if (buttonPressed.equals(whiteButton)) {
			rowModel.addColor(Color.WHITE);
		} else if (buttonPressed.equals(greyButton)) {
			rowModel.addColor(Color.GREY);
		} else if (buttonPressed.equals(orangeButton)) {
			rowModel.addColor(Color.ORANGE);
		} else if (buttonPressed.equals(pinkButton)) {
			rowModel.addColor(Color.PINK);
		} else if (buttonPressed.equals(redButton)) {
			rowModel.addColor(Color.RED);
		} else if (buttonPressed.equals(greenButton)) {
			rowModel.addColor(Color.GREEN);
		} else if (buttonPressed.equals(blueButton)) {
			rowModel.addColor(Color.BLUE);
		} else if (buttonPressed.equals(yellowButton)) {
			rowModel.addColor(Color.YELLOW);
		}
		buttonPressed.setDisable(true);
		if (rowModel.rowFullyDefined()) {
			disableAllColorButtons(true);
			disableSolveButton(false);
		}
		colorCircles();
	}

	@FXML
	protected void clearButtonPressed(ActionEvent event) {
		rowModel.reset();
		disableAllColorButtons(false);
		disableSolveButton(true);
		colorCircles();
	}

	@FXML
	protected void solveButtonPressed(ActionEvent event) {
		Solver solver = new Solver(rowModel.getRow());
		List<Row> result = solver.solve();
		for (Row row : result) {
			solutionTextArea.appendText(row.toString() + "\n");
		}
	}	
	
	private void disableAllColorButtons(boolean disable) {
		whiteButton.setDisable(disable);
		greyButton.setDisable(disable);
		orangeButton.setDisable(disable);
		pinkButton.setDisable(disable);
		redButton.setDisable(disable);
		greenButton.setDisable(disable);
		blueButton.setDisable(disable);
		orangeButton.setDisable(disable);
		yellowButton.setDisable(disable);
	}

	private void disableSolveButton(boolean disable) {
		solveButton.setDisable(disable);
	}

	private void colorCircles() {
		for (int i = 0; i < 4; i++) {
			Color color = rowModel.getColor(i);
			if (i == 0) {
				colorCircle(firstColorCircle, color);
			} else if (i == 1) {
				colorCircle(secondColorCircle, color);
			} else if (i == 2) {
				colorCircle(thirdColorCircle, color);
			} else if (i == 3) {
				colorCircle(fourthColorCircle, color);
			}
		}
	}

	private void colorCircle(Circle circle, Color color) {
		circle.setFill(paintForColor(color));
	}

	private Paint paintForColor(Color color) {
		if (Color.WHITE == color) {
			return javafx.scene.paint.Color.WHITE;
		} else if (Color.GREY == color) {
			return javafx.scene.paint.Color.GREY;
		} else if (Color.ORANGE == color) {
			return javafx.scene.paint.Color.ORANGE;
		} else if (Color.PINK == color) {
			return javafx.scene.paint.Color.PINK;
		} else if (Color.RED == color) {
			return javafx.scene.paint.Color.RED;
		} else if (Color.BLUE == color) {
			return javafx.scene.paint.Color.BLUE;
		} else if (Color.YELLOW == color) {
			return javafx.scene.paint.Color.YELLOW;
		} else if (Color.GREEN == color) {
			return javafx.scene.paint.Color.GREEN;
		} else {
			return javafx.scene.paint.Color.BLACK;
		}
	}
}
