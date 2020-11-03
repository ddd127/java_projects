package expression_parser.operations;

import expression_parser.expression.CommonExpression;

import java.util.Objects;

public abstract class UnaryOperation implements CommonExpression {
    private final CommonExpression operand;

    public UnaryOperation(CommonExpression operand) {
        this.operand = operand;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    public abstract String getSymbol();

    public abstract int getValue(int x);

    public String toString() {
        return getSymbol() + "(" + operand.toString() + ")";
    }

    public String toMiniString() {
        return getSymbol() + " " + operand.toString();
    }

    @Override
    public int evaluate(int x) {
        return getValue(operand.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getValue(operand.evaluate(x, y, z));
    }

    @Override
    public boolean isCommutable() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj.getClass() == this.getClass())) return false;
        UnaryOperation obj1 = (UnaryOperation) obj;
        return obj == this || operand.equals(obj1.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getClass());
    }
}
