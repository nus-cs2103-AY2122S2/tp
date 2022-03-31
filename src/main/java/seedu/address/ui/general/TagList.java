package seedu.address.ui.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiManager;
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

    public void setTagList(ObservableList<Tag> tagList) {
        tagListView.setItems(tagList);
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

    /**
     * Handles the event whenever the selected tag card changes.
     */
    public void handleSelectTag() {
        try {
            Tag tagSelected = tagListView.getSelectionModel().getSelectedItem();
            String filterCommand = String.format("%s %s", FilterCommand.COMMAND_WORD, tagSelected.getTagName());
            CommandResult commandResult = logic.execute(filterCommand);
            UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the event where user right-clicks on a tag card.
     */
    public void handleTagContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete Tag");
        delete.setOnAction(event -> {
            try {
                Index tagToDeleteIndex = Index.fromZeroBased(tagListView.getSelectionModel().getSelectedIndex() + 1);
                String deleteTagCommand = String.format("%s %s", DeleteTagCommand.COMMAND_WORD, tagToDeleteIndex);
                CommandResult commandResult = logic.execute(deleteTagCommand);
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (ParseException | CommandException e) {
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
            }
        });
        contextMenu.getItems().addAll(delete);
        tagListView.setContextMenu(contextMenu);
    }

    public ListView<Tag> getTagListView() {
        return tagListView;
    }
}
