package expression_parser.expression;

public interface CommonExpression extends TripleExpression, Expression {
    int getPriority();
    String toMiniString();
    boolean isCommutable();
}
