package net.matmercer.android.calculator.math;

import java.math.BigDecimal;

public class SubtractOperation extends CalcOperation {
    /**
     * You can read this constructor as:
     * op = new SubtractOperation(4, 5)
     * op.solve(4, 5) == -1
     * x is the left number of the operation.
     *
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public SubtractOperation(BigDecimal x, BigDecimal y) {
        super(x, y);
    }

    public SubtractOperation(BigDecimal y) {
        super(y);
    }

    @Override
    public BigDecimal solve() {
        return x.subtract(y);
    }
}
