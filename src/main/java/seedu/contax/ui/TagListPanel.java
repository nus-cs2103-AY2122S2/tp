package seedu.contax.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.contax.commons.core.LogsCenter;
import seedu.contax.model.tag.Tag;

/**
 * Panel containing a list of tags.
 */
public class TagListPanel extends UiPart<Region> {
    private static final String FXML = "TagListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TagListPanel.class);

    @FXML
    private ListView<Tag> tagListView;

    /**
     * Creates a {@code TagListPanel} with the given tag {@code ObservableList}.
     * @param tagList The specified tag list to be displayed.
     */
    public TagListPanel(ObservableList<Tag> tagList) {
        super(FXML);
        tagListView.setItems(tagList);
        tagListView.setCellFactory(tagView -> new TagListViewCell());
    }

    class TagListViewCell extends ListCell<Tag> {

        private final TagCard card;

        /**
         * Initializes the UI elements for this cell.
         */
        TagListViewCell() {
            this.card = new TagCard();
        }

        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(card.updateModel(tag, getIndex() + 1));
            }
        }
    }
}
