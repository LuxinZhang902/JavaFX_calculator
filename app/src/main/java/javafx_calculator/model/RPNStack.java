package javafx_calculator.model;

import java.util.function.BinaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RPNStack {
    ObservableList<Double> myStack;


    public RPNStack() {
        myStack = FXCollections.observableArrayList();
    }

    public int size() {
        return myStack.size();
    }

    public ObservableList<Double> getList() {
        return myStack;
    }

    public void pushNum(double d) {
        myStack.add(d);
    }

    protected void binOp(BinaryOperator<Double> op) {
        if (myStack.size() < 2) {
            throw new IllegalStateException("That operations requires 2 operands on the stack, but there were only" +
                    myStack.size());
        }
        double d1 = myStack.remove(myStack.size()-1);
        double d2 = myStack.remove(myStack.size()-1);
        myStack.add(op.apply(d2, d1));

    }

    public void add() {
        binOp((a, b) -> {
            return a + b;
        });
    }

    public void subtract() {
        binOp((a, b) -> {
            return a - b;
        });
    }

    public void divide() {
        binOp((a, b) -> {
            return a / b;
        });
    }

    public void times() {
        binOp((a, b) -> {
            return a * b;
        });
    }

}