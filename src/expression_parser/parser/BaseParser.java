package expression_parser.parser;

import expression_parser.expressionException.*;

public class BaseParser {
    private final ExpressionSource source;
    private final StringBuilder buffer;
    protected char ch;

    protected BaseParser(final ExpressionSource source) {
        this.source = source;
        this.buffer = new StringBuilder();
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        if (source.test(expected)) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (!test(c)) {
            throw new UnexpectedCharacterException("Expected '" + c + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected String errorMessage(final String message) {
        return source.errorMessage(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }
}
