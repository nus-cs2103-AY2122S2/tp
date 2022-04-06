package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.trackermon.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    void testToString() {

        String testEmoji = "laugh\uD83D\uDE00";
        String testClean = "laugh";

        Comment testCleanComment = new Comment(testClean);
        Comment testEmojiComment = new Comment("laugh\uD83D\uDE00"); //removal of emojis

        assertNotEquals("laugh\uD83D\uDE00", testEmojiComment.comment); //Emoji should be removed
        assertEquals("laugh", testCleanComment.comment);
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
