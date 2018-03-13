package net.matmercer.android.calculator.math;

import net.matmercer.android.calculator.math.operations.CalcOperation;

import java.math.BigDecimal;

public class SimpleCalculator extends Calculator {
    @Override
    public BigDecimal addOperation(CalcOperation op) {

        operation = op;

        if (currentScreen != null) {
            op.setX(currentScreen);
        }

        currentScreen = operation.solve();


        return currentScreen;
    }

    @Override
    public BigDecimal equals() {
        operation.setX(operation.solve());

        currentScreen = operation.solve();

        return currentScreen;
    }

    @Override
    public BigDecimal negate() {
        currentScreen = currentScreen.negate();

        operation.setX(currentScreen);

        return currentScreen;
    }

    @Override
    public void ce() {
        currentScreen = null;
    }
}
