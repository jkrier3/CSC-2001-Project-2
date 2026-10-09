import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: write unit tests for Parser
class ParserTest {

    @Test
    void prec(){
        assertEquals(1, Parser.precedence("+"));
    }
    @Test
    void isLeft(){
        assertTrue(isLeftAssociative);
    }

}
