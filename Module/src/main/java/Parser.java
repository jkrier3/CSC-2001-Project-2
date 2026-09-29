// Parsers for postfix and infix expressions
static AST parsePostFix(String input){
    if(input.equals("")){
        throw new IllegalArgumentException("empty input");
    }
    Deque<AST> stack = new ArrayDeque<>();
    int operands = 0;
    int operators = 0;
    for(String token : input.split("\\s+")){
        if(isNumber(token)){
            stack.push(new Num(Double.parseDouble(token)));
            operands++;
        }else if(isOp(token)){
            if(stack.size() < 2){
                throw new IllegalArgumentException("insufficient operands");
            }
            AST right = stack.pop();
            AST left = stack.pop();
            stack.push(new Binop(token, left, right));
            operators++;
        }else{
            throw new IllegalArgumentException("invalid token");
        }
    }
    if(operands <= operators){
        throw new IllegalArgumentException("insufficient operands");
    }
    if(operators < operands-1){
        throw new IllegalArgumentException("too many operands");
    }
    return stack.pop();
}
static boolean isNumber(String s){
    try{
        Double.parseDouble(s);
        return true;
    }catch(NumberFormatException e){
        return false;
    }
}
static boolean isOp(String s){
    if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
        return true;
    }else{
        return false;
    }
}

public static void main(String[] args) {
    System.out.println(parsePostFix("2 2 +"));

}