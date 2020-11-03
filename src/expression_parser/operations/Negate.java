package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class Negate extends UnaryOperation {
    public Negate(CommonExpression operand) {
        super(operand);
    }

    @Override
    public int getValue(int x) {
        return -1 * x;
    }

    public int getPriority() {
        return 4;
    }

    public String getSymbol() {
        return "-";
    }
}
