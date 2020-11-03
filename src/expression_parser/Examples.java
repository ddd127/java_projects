package expression_parser;

import expression_parser.expression.CommonExpression;
import expression_parser.expressionException.EvaluateException;
import expression_parser.expressionException.ParsingException;
import expression_parser.parser.*;

public class Examples {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        String[] strings = new String[]{"x + 2",
                "x - 3",
                "(x * 2) / (x - 2)",
                "(x * 2) / (x - 1)",
                "(x ** 4) // 2",
                "(x * 2 / (x - 2)",
                "x * 2) / (x - 2)"};
        for (String str : strings) {
            System.out.println("Parsing: " + str);
            try {
                CommonExpression expr = parser.parse(str);
                System.out.println(expr.toMiniString());
                try {
                    System.out.println(expr.evaluate(1, 1, 1));
                } catch (EvaluateException e) {
                    System.out.println(e.getMessage());
                }
            } catch (ParsingException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }
}
