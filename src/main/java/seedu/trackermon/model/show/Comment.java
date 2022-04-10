package seedu.trackermon.model.show;

import static seedu.trackermon.commons.util.AppUtil.checkArgument;

/***
 * Represents a Show's comment.
 */
public class Comment {

    public static final String MESSAGE_CONSTRAINTS =
            "Comment is limited to standard ASCII characters and can contain up to 300000 ASCII characters";

    public static final String VALIDATION_REGEX_COMMENT = "[\\p{ASCII}]{0,300000}";

    public final String comment;

    /**
     * Constructs a {@code Comment} with the provided {@code String}.
     * @param comment {@code String}.
     */
    public Comment(String comment) {
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        this.comment = comment;
    }

    /**
     * Returns a {@code Sting} representation fo the comment.
     */
    @Override
    public String toString() {
        return comment;
    }

    /**
     * Returns whether two objects are equal, or share the same comment.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Comment
                && comment.equals(((Comment) other).comment));
    }

    /**
     * Returns the hashcode of the {@code Comment}.
     */
    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
