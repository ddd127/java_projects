package expression_parser.expression;

import expression_parser.parser.ExpressionSource;

public interface Parser {
    CommonExpression parse(ExpressionSource source);
}
