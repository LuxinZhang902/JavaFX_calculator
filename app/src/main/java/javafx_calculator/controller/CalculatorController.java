package javafx_calculator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    @FXML
    TextField currentNumber;
    @FXML
    NumButtonController numButtonController;
    public void initialize(URL location,  ResourceBundle resources) {
        numButtonController.currentNumber= currentNumber;
    }
}
