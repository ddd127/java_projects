package expression_parser.operations;

import expression_parser.expressionException.InvalidArgumentException;
import expression_parser.expression.CommonExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        checkDivOverflow(x, y);
        if (y == 0) {
            throw new InvalidArgumentException("Division by zero exception");
        }
        return x / y;
    }
}
