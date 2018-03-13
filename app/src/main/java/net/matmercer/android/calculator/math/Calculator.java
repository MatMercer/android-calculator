package net.matmercer.android.calculator.math;

import net.matmercer.android.calculator.math.operations.CalcOperation;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Calculator {
    protected Queue<CalcOperation> operations;
    protected CalcOperation operation;

    protected BigDecimal currentScreen;

    public Calculator() {
        operations = new LinkedList<>();
    }

    public abstract BigDecimal addOperation(CalcOperation op);

    public abstract BigDecimal equals();

    public abstract BigDecimal negate();

    public abstract void ce();

    public BigDecimal getCurrentScreen() {
        return currentScreen;
    }
}
