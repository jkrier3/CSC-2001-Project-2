import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ASTTest {
    @Test
    void num(){
        Num n = new Num(1.0);
        assertEquals(1.0, n.eval());
        Num n1 = new Num(-6.7);
        assertEquals(-6.7, n1.eval());
    }
    @Test
    void binop(){
        Binop plus = new Binop("+", new Num(2), new Num(3));
        assertEquals(5.0, plus.eval());
        Binop minus = new Binop("-", new Num(8), new Num(3));
        assertEquals(5.0, minus.eval());
        Binop mult = new Binop("*", new Num(2), new Num(3));
        assertEquals(6.0, mult.eval());
        Binop divide = new Binop("/", new Num(8), new Num(2));
        assertEquals(4.0, divide.eval());
        Binop exp = new Binop("^", new Num(2), new Num(3));
        assertEquals(8.0, exp.eval());

        AST addition = new Binop("+", new Num(2), new Num(3));
        AST multiplication = new Binop("*", addition, new Num(4));
        assertEquals(20.0, multiplication.eval());

        Binop error = new Binop("hello", new Num(2), new Num(3));
        assertThrows(IllegalArgumentException.class, () -> error.eval());
    }

}
