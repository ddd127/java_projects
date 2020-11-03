package expression_parser.expressionException;

public class UnexpectedEndException extends ParsingException {
    public UnexpectedEndException(String message) {
        super(message);
    }
}
