package seedu.address.ui.general;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.logic.Logic;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;

public class TagList extends UiPart<Region> {
    private static final String FXML = "TagList.fxml";
    private Logic logic;
    @FXML
    private ListView<Tag> tagListView;

    /**
     * Creates a {@code TagList}.
     */
    public TagList(Logic logic) {
        super(FXML);
        this.logic = logic;
    }

    public void setTagList(List<Tag> tagList) {
        tagListView = new ListView<>();
        ObservableList<Tag> observableTagList = FXCollections.observableList(tagList);
        tagListView.setItems(observableTagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
    }


    class TagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TagCard(tag, getIndex() + 1,
                        logic.getModel().countPersonsInTag(tag)).getRoot());
            }
        }
    }

    public ListView<Tag> getTagListView() {
        return tagListView;
    }
}
