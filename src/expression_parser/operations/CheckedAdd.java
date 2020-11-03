package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    public int getValue(int x, int y) {
        checkAddOverflow(x, y);
        return y + x;
    }
}
