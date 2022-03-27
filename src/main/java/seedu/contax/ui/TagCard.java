package seedu.contax.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.tag.Tag;

/**
 * An UI components that displays the information in an {@code Tag}.
 */
public class TagCard extends UiPart<Region> implements RecyclableCard<Tag> {
    private static final String FXML = "TagListCard.fxml";

    public int displayedIndex;
    public Tag tagModel;

    @FXML
    private Label id;

    @FXML
    private Label tagName;

    /**
     * Creates an empty {@code TagCard}, with all fields set to blank.
     */
    public TagCard() {
        super(FXML);

        id.setText("");
        tagName.setText("");
    }

    /**
     * Updates the contents of this card with the supplied Tag model and index to display.
     *
     * @param tagModel The specified tag to display.
     * @param displayedIndex The index of the tag to be displayed.
     */
    public Node updateModel(Tag tagModel, int displayedIndex) {
        this.displayedIndex = displayedIndex;
        this.tagModel = tagModel;

        id.setText(displayedIndex + ". ");
        tagName.setText(tagModel.getTagNameString());

        return this.getRoot();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TagCard)) {
            return false;
        }

        return ((TagCard) o).tagModel.equals(tagModel);
    }
}
