package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public class SumOperation extends CalcOperation{
    /**
     * You can read this constructor as:
     * op = new SumOperation(4, 5)
     * op.solve(4, 5) == 9
     * x is the left number of the operation.
     *
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public SumOperation(BigDecimal x, BigDecimal y) {
        super(x, y);
    }

    public SumOperation(BigDecimal y) {
        super(y);
    }

    @Override
    public BigDecimal solve() {
        return x.add(y);
    }
}
