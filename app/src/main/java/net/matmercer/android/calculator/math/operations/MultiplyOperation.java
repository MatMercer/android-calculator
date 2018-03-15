package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public class MultiplyOperation extends CalcOperation {
    /**
     * You can read this constructor as:
     * op = new MultiplyOperation(4, 5)
     * op.solve(4, 5) == 20
     * x is the left number of the operation.
     *
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public MultiplyOperation(BigDecimal x, BigDecimal y) {
        super(x, y);
    }

    public MultiplyOperation(BigDecimal y) {
        super(y);
    }

    @Override
    public BigDecimal solve() {
        return x.multiply(y);
    }
}
