package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attendance.Comment;

/**
 * An UI component that displays information of a {@code Comment}.
 */
public class CommentCard extends UiPart<Region> {

    private static final String FXML = "CommentCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Comment comment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label commentHeader;
    @FXML
    private Label commentText;

    /**
     * Creates a {@code CommentCode} with the given {@code Comment} and index to display.
     */
    public CommentCard(Comment comment) {
        super(FXML);
        this.comment = comment;
        commentHeader.setText("Comments");
        commentText.setText(comment.toString().equals("") ? "None" : comment.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCard)) {
            return false;
        }

        // state check
        CommentCard card = (CommentCard) other;
        return comment.equals(card.comment);
    }
}
