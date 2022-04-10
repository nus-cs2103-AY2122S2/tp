package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Comment}.
 */
public class CommentTest {

    /**
     * Test constructor of {@code Comment} for null input.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    /**
     * Test return {@code String} for {@code Comment} toString method.
     */
    @Test
    void testToString() {
        String test1 = "laugh1";
        Comment testComment1 = new Comment(test1);
        assertEquals(test1, testComment1.comment);
    }

    @Test
    void testEquals() {
        String testClean = "laugh";
        Comment refComment = new Comment(testClean);
        Comment testComment = new Comment(testClean);

        assertTrue(refComment.equals(testComment));
    }

    @Test
    void testHashCode() {
        String testClean = "laugh";
        Comment testComment = new Comment(testClean);
        assertEquals(testClean.hashCode(), testComment.hashCode());
    }

}
