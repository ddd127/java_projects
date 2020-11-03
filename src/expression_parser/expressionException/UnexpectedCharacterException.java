package expression_parser.expressionException;

public class UnexpectedCharacterException extends ParsingException {
    public UnexpectedCharacterException(String message) {
        super(message);
    }
}
