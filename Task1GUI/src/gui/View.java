package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class View {
    @FXML
    private Label resultLabel;

    void displayLabel(double result) {
        resultLabel.setText(Double.toString(result));
    }
}