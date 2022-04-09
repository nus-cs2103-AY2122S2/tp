package seedu.unite.ui.general;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.UiPart;

/**
 * An UI component that displays information of a {@code Tag}.
 */
public class TagCard extends UiPart<Region> {

    private static final String FXML = "TagCard.fxml";

    public final Tag tag;

    public final int personInTag;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label tagName;
    @FXML
    private Label personInTagCount;
    @FXML
    private Label remark;

    /**
     * Creates a {@code TagCard} with the given {@code tag} and index to display.
     */
    public TagCard(Tag tag, int displayedIndex, int personInTag) {
        super(FXML);
        this.tag = tag;
        this.personInTag = personInTag;
        id.setText(displayedIndex + ". ");
        tagName.setText(tag.getTagName());
        personInTagCount.setText(personInTag + " " + (personInTag > 1 ? "people" : "person"));
        remark.setText(tag.getRemark().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCard)) {
            return false;
        }

        // state check
        TagCard tagCard = (TagCard) other;
        return id.getText().equals(tagCard.id.getText())
                && tagName.equals(tagCard.tagName)
                && remark.equals(tagCard.remark);
    }
}
