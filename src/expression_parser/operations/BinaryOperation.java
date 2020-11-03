package expression_parser.operations;

import expression_parser.expression.CommonExpression;
import expression_parser.expressionException.EvaluateOverflowException;

import java.util.Objects;

public abstract class BinaryOperation implements CommonExpression {
    private final CommonExpression first;
    private final CommonExpression second;

    public BinaryOperation(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public abstract int getPriority();

    public abstract String getSymbol();

    public abstract int getValue(int x, int y);

    public String toString() {
        return "(" + first.toString() + getSymbol() + second.toString() + ")";
    }

    public String toMiniString() {
        StringBuilder builder = new StringBuilder();
        if (first.getPriority() < getPriority()) {
            builder.append("(").append(first.toMiniString()).append(")");
        } else {
            builder.append(first.toMiniString());
        }
        builder.append(getSymbol());
        if (second.getPriority() > getPriority() ||
                (second.getPriority() == getPriority() &&
                        (second.isCommutable() && isCommutable()))
        ) {
            builder.append(second.toMiniString());
        } else {
            builder.append("(").append(second.toMiniString()).append(")");
        }
        return builder.toString();
    }

    @Override
    public int evaluate(int x) {
        return getValue(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getValue(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == this.getClass())) return false;
        BinaryOperation obj1 = (BinaryOperation) obj;
        return obj == this || (
                first.equals(obj1.first) &&
                second.equals(obj1.second)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getClass());
    }

    protected void checkAddOverflow(int x, int y) {
        if (x > 0 ? y > Integer.MAX_VALUE - x
                : y < Integer.MIN_VALUE - x) {
            throw new EvaluateOverflowException("Overflow");
        }
    }

    protected void checkSubOverflow(int x, int y) {
        if (y > 0 ? x < Integer.MIN_VALUE + y
                : x > Integer.MAX_VALUE + y) {
            throw new EvaluateOverflowException("Overflow");
        }
    }

    protected void checkDivOverflow(int x, int y) {
        if ((x == Integer.MIN_VALUE) && (y == -1)) {
            throw new EvaluateOverflowException("Overflow");
        }
    }

    protected void checkMulOverflow(int x, int y) {
        if (x > 0 ? y > Integer.MAX_VALUE/x
                || y < Integer.MIN_VALUE/x
                : (x < -1 ? y > Integer.MIN_VALUE/x
                || y < Integer.MAX_VALUE/x
                : x == -1
                && y == Integer.MIN_VALUE) ) {
            throw new EvaluateOverflowException("Overflow");
        }
    }
}
