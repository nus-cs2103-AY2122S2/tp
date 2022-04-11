package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    private final Comment comment = new Comment("First comment");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    public void isValidComment() {
        // null comment
        assertThrows(NullPointerException.class, () -> Comment.isValidComment(null));

        // invalid comment
        assertFalse(Comment.isValidComment("")); // empty string
        assertFalse(Comment.isValidComment(" ")); // spaces only

        // valid comment
        assertTrue(Comment.isValidComment("i")); // a single character
        assertTrue(Comment.isValidComment("Valid comment entered")); // a sentence
    }

    @Test
    public void setCommentString() {

        String newComment = "New comment";
        Comment commentCopy = new Comment(comment.getCommentString());
        commentCopy.setCommentString(newComment);

        // update comment
        assertEquals(commentCopy.getCommentString(), newComment);
    }
}
