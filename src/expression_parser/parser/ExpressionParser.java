package expression_parser.parser;

import expression_parser.expression.CommonExpression;
import expression_parser.expressionException.*;
import expression_parser.operations.*;

import java.util.List;

import static expression_parser.parser.BinOperations.*;

public class ExpressionParser implements expression_parser.expression.Parser {
    public CommonExpression parse(String expression) {
        return parse(new StringExpressionSource(expression));
    }

    public CommonExpression parse(ExpressionSource source) {
        return new Parser(source).parse();
    }

    private static class Parser extends BaseParser {
        private static final List<List<BinOperations>> priorityList = List.of(
                List.of(Add, Sub),
                List.of(Mul, Div),
                List.of(Pow, Log)
        );
        private static final int maxPriority = priorityList.size() - 1;

        public Parser(ExpressionSource source) {
            super(source);
        }

        public CommonExpression parse() {
            final CommonExpression answer = parseExpression(-1);
            if (test('\0')) {
                return answer;
            }
            throw new NotAnOperationException(errorMessage("End of expression_parser.expression or Operation expected, found: "));
        }

        private CommonExpression parseExpression(int priority) {
            if (priority > maxPriority) {
                return parseElement();
            }
            CommonExpression firstOperand = parseExpression(priority + 1);
            for (BinOperations sign = getOperation(priority); sign != null; sign = getOperation(priority)) {
                CommonExpression secondOperand = parseExpression(priority + 1);
                firstOperand = parseOperation(firstOperand, secondOperand, sign);
            }
            return firstOperand;
        }

        private BinOperations getOperation(int priority) {
            skipWhitespace();
            if (priority >= 0 && priority <= maxPriority) {
                for (BinOperations str : priorityList.get(priority)) {
                    if (test(str.toString())) {
                        return str;
                    }
                }
            }
            return null;
        }

        private CommonExpression parseOperation(CommonExpression first, CommonExpression second, BinOperations sign) {
            switch (sign) {
                case Add: return new CheckedAdd(first, second);
                case Sub: return new CheckedSubtract(first, second);
                case Mul: return new CheckedMultiply(first, second);
                case Div: return new CheckedDivide(first, second);
                case Pow: return new Pow(first, second);
                case Log: return new Log(first, second);
                default:
                    throw new UnexpectedCharacterException(errorMessage("Invalid operation sign : "));
            }
        }

        private CommonExpression parseElement() {
            skipWhitespace();
            if (test('-')) {
                return parseMinus();
            } else if (test('(')) {
                return parseParentheses();
            } else if (between('x', 'z')) {
                CommonExpression result = new Variable(Character.toString(ch));
                nextChar();
                return result;
            } else if (between('0', '9')) {
                String number = copyDigits();
                try {
                    return new Const(Integer.parseInt(number));
                } catch (NumberFormatException e) {
                    throw new ParsingOverflowException(errorMessage(number));
                }
            } else if (test('\0')) {
                throw new UnexpectedEndException(errorMessage("Unexpected end"));
            } else if (test(')')) {
                throw new NoOpeningParenthesisException(errorMessage(""));
            } else {
                throw new NotAnArgumentException(errorMessage(""));
            }
        }

        private CommonExpression parseParentheses() {
            CommonExpression result = parseExpression(-1);
            if (test(')')) {
                return result;
            }
            throw new NoClosingParenthesisException(errorMessage("Expected : ')'"));
        }

        private CommonExpression parseMinus() {
            if (between('1', '9')) {
                String number = "-" + copyDigits();
                try {
                    return new Const(Integer.parseInt(number));
                } catch (NumberFormatException e) {
                    throw new ParsingOverflowException(errorMessage(number));
                }
            }
            return new CheckedNegate(parseElement());
        }

        private String copyDigits() {
            StringBuilder sb = new StringBuilder();
            while (between('0', '9')) {
                sb.append(ch);
                nextChar();
            }
            return sb.toString();
        }
    }
}