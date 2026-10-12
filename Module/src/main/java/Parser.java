import java.util.ArrayDeque;
import java.util.Deque;

// Parsers for postfix and infix expressions
public class Parser {
    public static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) {
            return 1;
        } else if (op.equals("*") || op.equals("/")) {
            return 2;
        } else if (op.equals("^")) {
            return 3;
        } else {
            return -1;
        }
    }

    static boolean isLeftAssociative(String op) {
        return !op.equals("^");
    }

    static void apply(Deque<AST> stack, String operator) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("insufficient operands");
        }
        AST right = stack.pop();
        AST left = stack.pop();
        stack.push(new Binop(operator, left, right));

    }


    static AST parsePostfix(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("empty input");
        }
        Deque<AST> stack = new ArrayDeque<>();
        int operands = 0;
        int operators = 0;
        for (String token : input.split("\\s+")) {
            if (isNumber(token)) {
                stack.push(new Num(Double.parseDouble(token)));
                operands++;
            } else if (isOp(token)) {
                apply(stack, token);
                operators++;
            } else {
                throw new IllegalArgumentException("invalid token");
            }
        }
        if (operators < operands - 1) {
            throw new IllegalArgumentException("too many operands");
        }
        return stack.pop();
    }

    static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isOp(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
            return true;
        } else {
            return false;
        }
    }

    public static AST parseInfix(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("empty input");
        }
        Deque<AST> operands = new ArrayDeque<>();
        Deque<String> operators = new ArrayDeque<>();
        for (String token : input.split("\\s+")) {
            if (isNumber(token)) {
                operands.push(new Num(Double.parseDouble(token)));
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    apply(operands, operators.pop());
                }
                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("mismatched close paren");
                }
                operators.pop();
            } else if (isOp(token)) {
                while (!operators.isEmpty() &&
                        !operators.peek().equals("(") &&
                        (precedence(operators.peek()) > precedence(token) || (precedence(operators.peek()) == precedence(token) &&
                                isLeftAssociative(token)))) {
                    apply(operands, operators.pop());
                }
                operators.push(token);
            } else {
                throw new IllegalArgumentException("invalid token");
            }

        }
        while (!operators.isEmpty()) {
            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("mismatched open paren");
            }
            apply(operands, operators.pop());
        }
        if (operands.size() != 1) {
            throw new IllegalArgumentException("too many operands");
        }
        return operands.pop();


    }

}


