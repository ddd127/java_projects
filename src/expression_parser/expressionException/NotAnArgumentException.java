package expression_parser.expressionException;

public class NotAnArgumentException extends UnexpectedCharacterException {
    public NotAnArgumentException(String message) {
        super(message);
    }
}
