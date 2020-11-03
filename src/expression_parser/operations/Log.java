package expression_parser.operations;

import expression_parser.expressionException.*;
import expression_parser.expression.CommonExpression;

public class Log extends BinaryOperation {
    public Log(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int argument, int base) {
        if (argument < 1 || base < 2) {
            throw new InvalidArgumentException("Exception : Log(" + base + ", " + argument + ")");
        }
        int result = -1;
        int i = 1;
        while (i <= argument) {
            result++;
            try {
                checkMulOverflow(i, base);
            } catch (EvaluateOverflowException e) {
                return result;
            }
            i *= base;
        }
        return result;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public String getSymbol() {
        return " // ";
    }

    @Override
    public boolean isCommutable() {
        return false;
    }
}
