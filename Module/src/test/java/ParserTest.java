import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: write unit tests for Parser
class ParserTest {

    @Test
    void postFixAndApply(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("hello").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("+").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 +").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 2 2 2 +").eval());
        assertEquals(14.0, Parser.parsePostfix("2 3 4 + *").eval());
    }
    @Test
    void isLeftAndPrec(){
        assertTrue(Parser.isLeftAssociative("+"));
        assertTrue(Parser.isLeftAssociative("-"));
        assertFalse(Parser.isLeftAssociative("^"));
        assertEquals(1, Parser.precedence("+"));
        assertEquals(1, Parser.precedence("-"));
        assertEquals(2, Parser.precedence("*"));
        assertEquals(2, Parser.precedence("/"));
        assertEquals(3, Parser.precedence("^"));
        assertEquals(-1, Parser.precedence("hello"));
    }

    @Test
    void inFix(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("( 3 + 4 * 2").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("3 + 4 ) * 2").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("quinn").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("3 + 3 + 3 3").eval());
        assertEquals(14.0, Parser.parseInfix("( 3 + 4 ) * 2").eval());
        assertEquals(11.0, Parser.parseInfix("3 + 4 * 2").eval());
        assertEquals(5.0, Parser.parseInfix("3 + 4 - 2").eval());
        assertEquals(19.0, Parser.parseInfix("3 + 4 ^ 2").eval());
        assertEquals(1.0, Parser.parseInfix("3 - 4 / 2").eval());
        assertEquals(49.0, Parser.parseInfix("( 3 + 4 ) ^ 2").eval());
        assertEquals(10.0, Parser.parseInfix("( ( 3 + 4 ) - 2 ) * 2").eval());


    }


}
