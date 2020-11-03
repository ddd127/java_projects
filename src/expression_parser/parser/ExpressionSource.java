package expression_parser.parser;

public interface ExpressionSource {
    boolean hasNext();
    char next();
    String errorMessage(String message);
    boolean test(String str);
}
