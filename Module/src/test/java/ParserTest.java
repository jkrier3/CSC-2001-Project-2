import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: write unit tests for Parser
class ParserTest {

    @Test
    void postFix(){
        assertEquals(14.0, Parser.parsePostfix("2 3 4 + *").eval());
    }
    @Test
    void isLeft(){
        assertTrue(Parser.isLeftAssociative("+"));
    }


}
