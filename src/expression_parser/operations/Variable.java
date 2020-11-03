package expression_parser.operations;

import expression_parser.expression.CommonExpression;

public class Variable implements CommonExpression {
    private final String str;

    public Variable(String str) {
        this.str = str;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (str) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj == this || (obj.getClass() == Variable.class && this.str.equals(((Variable) obj).str));
    }

    @Override
    public int hashCode() {
        return str.hashCode();
    }

    @Override
    public boolean isCommutable() {
        return true;
    }
}
