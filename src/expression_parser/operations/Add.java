package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class Add extends BinaryOperation {
    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        return x + y;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSymbol() {
        return " + ";
    }

    @Override
    public boolean isCommutable() {
        return true;
    }
}
