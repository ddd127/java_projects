package expression_parser.operations;

import expression_parser.expressionException.InvalidArgumentException;
import expression_parser.expression.CommonExpression;

public class Pow extends BinaryOperation {
    public Pow(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        if (y < 0) {
            throw new InvalidArgumentException("Exception : " + toString());
        }
        if (y == 0) {
            if (x == 0) {
                throw new InvalidArgumentException("Exception : " + toString());
            }
            return 1;
        }
        if (y == 1) {
            return x;
        }
        int r = 1;
        while (y != 0) {
            if (y % 2 == 1) {
                checkMulOverflow(r, x);
                r = r * x;
                y = y - 1;
            }
            y = y / 2;
            if (y != 0) {
                checkMulOverflow(x, x);
                x = x * x;
            }
        }
        return r;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public String getSymbol() {
        return " ** ";
    }

    @Override
    public boolean isCommutable() {
        return false;
    }
}
