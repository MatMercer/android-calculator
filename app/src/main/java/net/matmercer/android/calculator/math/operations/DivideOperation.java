package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public class DivideOperation extends CalcOperation {
    /**
     * You can read this constructor as:
     * op = new DivideOperation(4, 5)
     * op.solve(4, 5) == 0.8
     * x is the left number of the operation.
     *
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public DivideOperation(BigDecimal x, BigDecimal y) {
        super(x, y);
    }

    public DivideOperation(BigDecimal y) {
        super(y);
    }

    @Override
    public BigDecimal solve() {
        return x.divide(y, 9, BigDecimal.ROUND_HALF_EVEN);
    }
}
