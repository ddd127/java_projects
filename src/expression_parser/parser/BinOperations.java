package expression_parser.parser;

public enum BinOperations {
    Add,
    Sub,
    Mul,
    Div,
    Log,
    Pow,
    RShift,
    LShift;

    @Override
    public String toString() {
        switch (this) {
            case Add: return "+";
            case Sub: return "-";
            case Mul: return "*";
            case Div: return "/";
            case Pow: return "**";
            case Log: return "//";
            case RShift: return ">>";
            case LShift: return "<<";
            default: return name();
        }
    }
}
