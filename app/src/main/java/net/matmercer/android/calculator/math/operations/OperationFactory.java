package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public class OperationFactory {
    public static CalcOperation createOperation(OperationType type, BigDecimal x, BigDecimal y) {
        switch (type) {
            case SUM:
                return new SumOperation(x, y);
            case SUBTRACT:
                return new SubtractOperation(x, y);
            case DIVIDE:
                return new DivideOperation(x, y);
            case MULTIPLY:
                return new MultiplyOperation(x, y);
        }

        return null;
    }
}
