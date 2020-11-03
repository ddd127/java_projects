package expression_parser.parser;

public class StringExpressionSource implements ExpressionSource {
    private final String source;
    private int pos;

    public StringExpressionSource(String source) {
        this.source = source;
        pos = -1;
    }

    @Override
    public boolean hasNext() {
        return pos < source.length() - 1;
    }

    @Override
    public char next() {
        return source.charAt(++pos);
    }

    @Override
    public String errorMessage(String message) {
        return
                message + " " +
                (pos < source.length() ? "'" + source.charAt(pos) + "' " : " ") +
                "at pos " + pos + " ";
    }

    public boolean test(String str) {
        if (source.startsWith(str, pos)) {
            pos += str.length() - 1;
            return true;
        } else {
            return false;
        }
    }
}
