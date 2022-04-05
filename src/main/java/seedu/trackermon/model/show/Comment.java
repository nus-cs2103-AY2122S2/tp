package seedu.trackermon.model.show;

import com.vdurmont.emoji.EmojiParser;

public class Comment {

    public final String comment;

    public Comment(String comment) {
        this.comment = EmojiParser.removeAllEmojis(comment);
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
