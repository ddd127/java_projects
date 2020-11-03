package expression_parser.expressionException;

public class NotAnOperationException extends UnexpectedCharacterException {
    public NotAnOperationException(String message) {
        super(message);
    }
}
