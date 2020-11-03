package expression_parser.operations;

import expression_parser.expressionException.EvaluateOverflowException;
import expression_parser.expression.CommonExpression;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression operand) {
        super(operand);
    }

    @Override
    public int getValue(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new EvaluateOverflowException("Overflow");
        }
        return -x;
    }
}
