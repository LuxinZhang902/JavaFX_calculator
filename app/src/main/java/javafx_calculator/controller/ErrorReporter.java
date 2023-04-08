package javafx_calculator.controller;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.*;

public class ErrorReporter implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
            //put this in for debugging: error.printStackTrace();
        e.printStackTrace();
        while(e.getCause() != null) {
            e = e.getCause();
        }
        Alert dialog = new Alert(AlertType.ERROR);
        dialog.setHeaderText(e.getClass().getName());
        dialog.setContentText(e.getMessage());
        dialog.showAndWait();
    }
}
