package seedu.contax.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.contax.model.tag.Tag;

/**
 * An UI components that displays the information in an {@code Tag}.
 */
public class TagCard extends UiPart<Region> {
    private static final String FXML = "TagListCard.fxml";

    public final int displayedIndex;
    public final Tag tagModel;

    @FXML
    private Label id;

    @FXML
    private Label tagName;

    /**
     * Creates a {@code TagCard} with the given {@code Tag} and index to display.
     * @param tagModel The specified tag to display.
     * @param displayedIndex The index of the tag to be displayed.
     */
    public TagCard(Tag tagModel, int displayedIndex) {
        super(FXML);
        this.displayedIndex = displayedIndex;
        this.tagModel = tagModel;

        id.setText(displayedIndex + ". ");
        tagName.setText(tagModel.getTagNameString());
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
