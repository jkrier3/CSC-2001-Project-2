import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

// TODO: write unit tests for Parser
class Parser2Test {

    @Test
    void postFixAndApply(){
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("hello").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("+").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("2 +").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostFix("2 2 2 2 +").eval());
        assertEquals(14.0, Parser.parsePostFix("2 3 4 + *").eval());
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
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInFix("").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInFix("( 3 + 4 * 2").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInFix("3 + 4 ) * 2").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInFix("quinn").eval());
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInFix("3 + 3 + 3 3").eval());
        assertEquals(14.0, Parser.parseInFix("( 3 + 4 ) * 2").eval());
        assertEquals(11.0, Parser.parseInFix("3 + 4 * 2").eval());
        assertEquals(5.0, Parser.parseInFix("3 + 4 - 2").eval());
        assertEquals(19.0, Parser.parseInFix("3 + 4 ^ 2").eval());
        assertEquals(1.0, Parser.parseInFix("3 - 4 / 2").eval());
        assertEquals(49.0, Parser.parseInFix("( 3 + 4 ) ^ 2").eval());
        assertEquals(10.0, Parser.parseInFix("( ( 3 + 4 ) - 2 ) * 2").eval());


    }
}
