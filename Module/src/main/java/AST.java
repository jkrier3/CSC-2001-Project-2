// AST

interface AST {
    double eval();
}

record Num(double value) implements AST {
    public double eval(){
        return value;
    }
}

record Binop(String op, AST left, AST right) implements AST {
    public double eval(){
        double l = left.eval();
        double r = right.eval();
        return switch (op){
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            case "/" -> l / r;
            case "^" -> {
                double power = r;
                double result = l;
                if(power==0){
                    yield 1;
                }else {
                    for (double i = 0; i < power - 1; i++) {
                        result = result * l;
                    }
                    yield result;
                }

            }
            default -> throw new IllegalArgumentException("invalid character: " + op);
        };
    }
}
