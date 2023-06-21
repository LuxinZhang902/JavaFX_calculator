package javafx_calculator.controller;
//package app;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@ExtendWith(ApplicationExtension.class)
public class NumButtonControllerTest {
    private TextField testText;
    private NumButtonController cont;
    private RPNStack model;
    @Start
    private void start(Stage stage) {
        testText = new TextField();
        model = mock(RPNStack.class);  //this is what is new here!
        cont = new NumButtonController(model);
        cont.currentNumber = testText;
    }
    @Test
    public void test_onNumberButton(FxRobot robot) {
        Platform.runLater(()->{
            Button b = new Button("7");
            cont.onNumberButton(new ActionEvent(b,  null));
//            cont.onNumberButton(new MouseEvent(b,  null));

        });
        WaitForAsyncUtils.waitForFxEvents();
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("7"));
    }


    private void addNums(String ... strs) {
        Platform.runLater(()->{
            for (String s: strs){
                Button b = new Button(s);
                cont.onNumberButton(new ActionEvent(b, null));
            } });
        WaitForAsyncUtils.waitForFxEvents();
    }
    @Test
    public void test_onNumberButton_7(FxRobot robot) {
        addNums("7");
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("7"));
    }
    @Test
    public void test_onNumberButton_pi(FxRobot robot) {
        addNums("3", ".", "1", "4");
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText("3.14"));
    }

    @Test
    void test_enterButton(FxRobot robot) {
        Platform.runLater(()->{
            testText.setText("1234.5");
            Button b = new Button("Enter");
            cont.onEnter(new ActionEvent(b,null));
        });
        WaitForAsyncUtils.waitForFxEvents();
        verify(model).pushNum(1234.5);
        verifyNoMoreInteractions(model);
        FxAssert.verifyThat(testText, TextInputControlMatchers.hasText(""));
    }

    @Test
    void test_add(){
        // 1. Create an instance of the ActionEvent class.
        ActionEvent event = new ActionEvent();

        // 2. Call the onPlus method on the controller instance, passing in the ActionEvent instance as a parameter.
        cont.onPlus(event);

        // 3. Verify that the expected behavior occurs.
        // Here, you could check that the expected calculation has been performed by examining the state of the model object.
        // For example, if the model object has a "result" property, you could check that it has been updated as expected.
//        assertEquals(4, cont.onPlus(event).getResult()); // Assumes that the model's result should be 4 after adding 2 and 2
    }




}
