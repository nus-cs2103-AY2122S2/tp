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

    @Override
    public String toString() {
        return comment;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Comment
                && comment.equals(((Comment) other).comment));
    }

    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
