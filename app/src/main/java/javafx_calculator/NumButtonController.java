package javafx_calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;


public class NumButtonController {
    @FXML
    TextField currentNumber;
    @FXML
    public void onNumberButton(ActionEvent ae) {
        Object source = ae.getSource();
        if (source instanceof Button) {
            Button btn = (Button) source;
            currentNumber.setText(currentNumber.getText() + btn.getText());
        } else {
            throw new IllegalArgumentException("Invalid source " +
                    source + " for ActionEvent");
        }
    }
}
