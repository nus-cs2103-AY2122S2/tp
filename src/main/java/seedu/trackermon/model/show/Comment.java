package seedu.trackermon.model.show;

import static seedu.trackermon.commons.util.AppUtil.checkArgument;

public class Comment {

    public static final String MESSAGE_CONSTRAINTS =
            "Comment is limited ASCII values and to 30000 characters";

    public static final Integer MAX_COMMENT_LENGTH = 300000;

    public static final String VALIDATION_REGEX_COMMENT = "[[\\p{Alnum}]*[\\p{Digit}]*[\\p{Punct}]*]{0,300000}";

    public final String comment;

    /**
     * Constructs a {@code Comment}.
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        String emojiFilter = "[(\\u00a9|\\u00ae|[\\u2000-\\u3300]|\\ud83c[\\ud000-\\udfff]|"
            + "\\ud83d[\\ud000-\\udfff]|\\ud83e[\\ud000-\\udfff])]";
        this.comment = comment.replaceAll(emojiFilter, "");
    }

    /**
     * Returns true is Comment is valid.
     */
    public static boolean isValidComment(String comment) {
        return comment.length() <= MAX_COMMENT_LENGTH;
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
