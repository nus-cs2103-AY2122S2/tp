package seedu.address.model.attendance;

import seedu.address.model.DisplayType;
import seedu.address.model.Displayable;

public class Comment implements Displayable {
    private static final DisplayType displayType = DisplayType.COMMENT;
    private String commentString;

    public Comment(String comment) {
        this.commentString = comment;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public String getCommentString() {
        return commentString;
    }

    public static boolean isValidComment(String s) {
        return !s.isEmpty();
    }

    @Override
    public DisplayType getDisplayType() {
        return displayType;
    }

    @Override
    public String toString() {
        return commentString;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return otherComment.getCommentString().equals(getCommentString());
    }
}