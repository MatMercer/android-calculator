package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public class TimesOperation extends CalcOperation {
    /**
     * You can read this constructor as:
     * op = new TimesOperation(4, 5)
     * op.solve(4, 5) == 20
     * x is the left number of the operation.
     *
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public TimesOperation(BigDecimal x, BigDecimal y) {
        super(x, y);
    }

    public TimesOperation(BigDecimal y) {
        super(y);
    }

    @Override
    public BigDecimal solve() {
        return x.multiply(y);
    }
}
