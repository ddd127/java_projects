package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        checkMulOverflow(x, y);
        return y * x;
    }
}
