import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: write unit tests for ArrayStack
class ArrayStackTest {
    private String[] str;
    private String[] str1;
    private ArrayStack s;
    private ArrayStack s1;

    @BeforeEach
    void setup() {
        str = new String[3];
        str1 = new String[]{"1", "2", null, null, null, null};
        s = new ArrayStack(str, 0);
        s1 = new ArrayStack(str1, 2);
    }

    @Test
    void tests() {
        assertThrows(IllegalArgumentException.class, () -> s.pop());
        assertThrows(IllegalArgumentException.class, () -> s.peek());
        assertTrue(s.isEmpty());
        assertFalse(s1.isEmpty());
        s.push("1");
        assertEquals(1, s.size());
        assertFalse(s.isEmpty());
        s.push("2");
        s.push("3");
        s.push("4");
        assertEquals(4, s.size());
        assertEquals("4", s.pop());
        assertEquals("3", s.pop());
        assertEquals("2", s.peek());
    }
}
