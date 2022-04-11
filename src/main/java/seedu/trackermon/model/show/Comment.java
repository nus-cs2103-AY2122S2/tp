package seedu.trackermon.model.show;

import static seedu.trackermon.commons.util.AppUtil.checkArgument;

public class Comment {

    public static final String MESSAGE_CONSTRAINTS =
            "Comment is limited to standard ASCII characters and can contain up to 300000 ASCII characters";

    public static final String VALIDATION_REGEX_COMMENT = "[\\p{ASCII}]{0,300000}";

    public final String comment;

    /**
     * Constructs a {@code Comment}.
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        this.comment = comment;
    }

    /**
     * Returns true if Comment is valid.
     */
    public static boolean isValidComment(String comment) {
        return comment.matches(VALIDATION_REGEX_COMMENT);
    }

    /**
     * Return {@code String} representation of {@code Comment}.
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
     * Returns the hashcode of the {@code Name}.
     */
    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
