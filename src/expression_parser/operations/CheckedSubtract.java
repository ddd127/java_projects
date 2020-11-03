package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        checkSubOverflow(x, y);
        return x - y;
    }
}
