package javafx_calculator;

import javafx.stage.Stage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ListViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;
@Disabled
@ExtendWith(ApplicationExtension.class)
class AppTest {
    App a;

    @Start
    public void start(Stage stage) throws IOException {
        a = new App();
        a.start(stage);
    }

    @Test
    void test_numButtons(FxRobot robot) {
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(""));
        String str = "123450.6789";
        for (char digit : str.toCharArray()) {
            if (digit == '.') {
                robot.clickOn("#dot");
            } else {
                robot.clickOn("" + digit);
            }
        }
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(str));

        robot.clickOn("#Enter");
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasItems(1));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasListCell(123450.6789));
    }
    private void clickButtonsFor(String str, FxRobot robot) {
        for (char digit : str.toCharArray()) {
            if (digit == '.') {
                robot.clickOn("#dot");
            } else {
                robot.clickOn("" + digit);
            }
        }
    }

    @Test
    void test_plusButton(FxRobot robot) {
        clickButtonsFor("123.5", robot);
        robot.clickOn("#Enter");
        clickButtonsFor("234.25", robot);
        robot.clickOn("#plus");
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasItems(1));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasListCell(357.75));
    }
    void test_button_helper(FxRobot robot, String btnName, String inp1,
                            String inp2, double ans, boolean useEnter){
        clickButtonsFor(inp1, robot);
        robot.clickOn("#Enter");
        clickButtonsFor(inp2, robot);
        if(useEnter) {
            robot.clickOn("#Enter");
        }
        robot.clickOn(btnName);
        FxAssert.verifyThat("#currentNumber", TextInputControlMatchers.hasText(""));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasItems(1));
        FxAssert.verifyThat("#rpnstack", ListViewMatchers.hasListCell(ans));
    }
    @Test
    void test_plusButton_wo_enter(FxRobot robot) {
        test_button_helper(robot, "#plus", "123.5", "234.25", 357.75, false);
    }
    @Test
    void test_plusButton_w_enter(FxRobot robot) {
        test_button_helper(robot, "#plus", "93.7", "24.3", 118, true);
    }


}