package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException();
        }
        return x / y;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getSymbol() {
        return " / ";
    }

    @Override
    public boolean isCommutable() {
        return false;
    }
}
