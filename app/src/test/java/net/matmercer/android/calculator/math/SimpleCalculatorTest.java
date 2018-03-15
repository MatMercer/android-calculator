package net.matmercer.android.calculator.math;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import net.matmercer.android.calculator.math.operations.CalcOperation;
import net.matmercer.android.calculator.math.operations.DivideOperation;
import net.matmercer.android.calculator.math.operations.MultiplyOperation;
import net.matmercer.android.calculator.math.operations.SubtractOperation;
import net.matmercer.android.calculator.math.operations.SumOperation;

import org.junit.Test;

import java.math.BigDecimal;

public class SimpleCalculatorTest {
    @NonNull
    private SimpleCalculator createCalc() {
        return new SimpleCalculator();
    }

    @NonNull SimpleCalculator mockCalculator() {
        SimpleCalculator calculator = createCalc();

        CalcOperation sum5 = new SumOperation(BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        CalcOperation sum3 = new SumOperation(BigDecimal.valueOf(3));
        CalcOperation sub10 = new SubtractOperation(BigDecimal.valueOf(10));

        calculator.addOperation(sum5); // 15
        calculator.addOperation(sum5); // 20
        calculator.addOperation(sum3); // 23
        calculator.addOperation(sub10); // 13

        return calculator;
    }

    @Test
    public void addOperation() throws Exception {
        SimpleCalculator calculator = mockCalculator();

        CalcOperation times100 = new MultiplyOperation(BigDecimal.valueOf(100d));
        CalcOperation divide7 = new DivideOperation(BigDecimal.valueOf(7d));

        calculator.addOperation(times100); // 1300
        calculator.addOperation(divide7); // 185.714285714

        Assert.assertEquals(185.714285714, calculator.getCurrentScreen().doubleValue());
    }

    @Test
    public void equals() throws Exception {
        SimpleCalculator calculator = mockCalculator();

        // -17
        for (int i = 0; i < 3; i += 1) {
            calculator.equals();
        }

        CalcOperation operation = new SumOperation(BigDecimal.valueOf(0.2d));

        // -16.8
        calculator.addOperation(operation);

        // -15.8
        for (int i = 0; i < 5; i += 1) {
            calculator.equals();
        }

        Assert.assertEquals(-15.8, calculator.getCurrentScreen().doubleValue());
    }

    @Test
    public void negate() throws Exception {
        // 13
        SimpleCalculator calculator = mockCalculator();

        // -13
        calculator.negate();

        // -26
        calculator.addOperation(new MultiplyOperation(BigDecimal.valueOf(2)));
        Assert.assertEquals(-26d, calculator.getCurrentScreen().doubleValue());

        calculator.negate();

        Assert.assertEquals(26d, calculator.getCurrentScreen().doubleValue());
    }

    @Test
    public void ce() throws Exception {
        SimpleCalculator calculator = mockCalculator();

        calculator.ce();

        Assert.assertEquals(null, calculator.getCurrentScreen());
    }

}