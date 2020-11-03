package expression_parser.expressionException;

public class NoOpeningParenthesisException extends UnexpectedCharacterException {
    public NoOpeningParenthesisException(String message) {
        super(message);
    }
}
