import org.example.RegexMatcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegexMatcherTest {

    @Test
    public void testMatchesValidRegex() {
        assertTrue(RegexMatcher.matches("\\d+", "12345"));
        assertFalse(RegexMatcher.matches("\\d+", "abc"));
    }

    @Test
    public void testMatchesNullInput() {
        try {
            RegexMatcher.matches(null, "test");
            fail("Expected IllegalArgumentException for null regex");
        } catch (IllegalArgumentException e) {
            assertEquals("regex and text must not be null", e.getMessage());
        }

        try {
            RegexMatcher.matches("test", null);
            fail("Expected IllegalArgumentException for null text");
        } catch (IllegalArgumentException e) {
            assertEquals("regex and text must not be null", e.getMessage());
        }
    }

    @Test
    public void testMatchesInvalidRegex() {
        assertFalse(RegexMatcher.matches("[", "test"));
        assertFalse(RegexMatcher.matches("(", "test"));
    }

    @Test
    public void testMatchesUsingCache() {
        assertTrue(RegexMatcher.matches("\\w+", "word123"));
        assertTrue(RegexMatcher.matches("\\w+", "anotherWord"));
    }
}