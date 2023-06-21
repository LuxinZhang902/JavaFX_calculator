package javafx_calculator.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx_calculator.model.RPNStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ErrorReportTest {
    @ExtendWith(ApplicationExtension.class)
    public class ErrorReporterTest {
        @Test
        public void test_alert(FxRobot robot) {
            ErrorReporter er = new ErrorReporter();
            Platform.runLater(()->er.uncaughtException(Thread.currentThread(),
                    new IllegalStateException("Test exception")));
            DialogPane errorDialog = robot.lookup(".dialog-pane").queryAs(DialogPane.class);
            assertEquals("java.lang.IllegalStateException", errorDialog.getHeaderText());
            assertEquals("Test exception", errorDialog.getContentText());
            Node ok = errorDialog.lookupButton(ButtonType.OK);
            assertNotNull(ok);
            robot.clickOn(ok);
        }
    }

}
