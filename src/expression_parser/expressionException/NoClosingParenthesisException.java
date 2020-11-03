package expression_parser.expressionException;

public class NoClosingParenthesisException extends UnexpectedCharacterException {
    public NoClosingParenthesisException(String message) {
        super(message);
    }
}
