package net.matmercer.android.calculator.math.operations;

import java.math.BigDecimal;

public abstract class CalcOperation {
    public enum CalcType {
        SUM,
        SUBTRACT,
        TIMES,
        DIVIDE,
        SQUAREROOT
    }

    BigDecimal x, y;

    /**
     * You can read this constructor as:
     *      op = new SubtractOperation(4, 5)
     *      op.solve(4, 5) == -1
     * x is the left number of the operation.
     * @param x The left number of the operation
     * @param y The right number of the operaton
     */
    public CalcOperation(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public CalcOperation(BigDecimal y) {
        this(BigDecimal.ZERO, y);
    }

    public abstract BigDecimal solve();

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public void setXY(BigDecimal x, BigDecimal y) {
        setX(x);
        setX(y);
    }
}
