package seedu.address.ui.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiManager;
import seedu.address.ui.UiPart;

public class TagList extends UiPart<Region> {
    private static final String FXML = "TagList.fxml";
    private final Logic logic;
    private final ContextMenu contextMenu = new ContextMenu();
    private final javafx.event.EventHandler<MouseEvent> disableRightClick = event -> {
        if (event.getButton() == MouseButton.SECONDARY) {
            event.consume();
        }
    };

    @FXML
    private ListView<Tag> tagListView;

    /**
     * Creates a {@code TagList}.
     */
    public TagList(Logic logic) {
        super(FXML);
        this.logic = logic;
        tagListView.addEventFilter(MouseEvent.ANY, disableRightClick);
    }

    public void setTagList(ObservableList<Tag> tagList) {
        tagListView.setItems(tagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
        tagListView.setContextMenu(contextMenu);
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
    public void handleSetFilteredList() {
        try {
            if (tagListView.getSelectionModel().getSelectedItem() != null) {
                Tag tagSelected = tagListView.getSelectionModel().getSelectedItem();
                PersonContainsTagPredicate personContainsTagPredicate = new PersonContainsTagPredicate(tagSelected);
                FilterCommand filterCommand = new FilterCommand(personContainsTagPredicate, tagSelected);
                CommandResult commandResult = filterCommand.execute(logic.getModel());
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            }
        } catch (CommandException e) {
            UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the event whenever the selected tag card changes. This handler will be triggered only on
     * mouse clicked (primary or secondary).
     */
    public void handleMouseSelect(MouseEvent mouseEvent) {
        // If primary key on mouse (left) is clicked, change the person list panel to the filtered list.
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            handleSetFilteredList();
        }
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            // If secondary key on mouse (right) is clicked, trigger context menu handler.
            handleTagContextMenu();
        }
    }

    /**
     * Handles the event whenever the selected tag card changes. This handler will be triggered only on arrow
     * key pressed, and will only be used to set the person list panel to the filtered list.
     */
    public void handleKeySelect(KeyEvent keyEvent) {
        if (tagListView.getSelectionModel().getSelectedItem() != null) {
            if (keyEvent.getCode().isArrowKey()) {
                tagListView.requestFocus();
                handleSetFilteredList();
            }
        }
    }

    /**
     * Handles the event where user right-clicks on a tag card.
     */
    public void handleTagContextMenu() {
        contextMenu.getItems().clear();
        MenuItem delete = new MenuItem("Delete Tag");
        contextMenu.getItems().addAll(delete);
        delete.setOnAction(event -> {
            try {
                Index tagToDeleteIndex = Index.fromZeroBased(tagListView.getSelectionModel().getSelectedIndex());
                DeleteTagCommand deleteTagCommand = new DeleteTagCommand(tagToDeleteIndex);
                CommandResult commandResult = deleteTagCommand.execute(logic.getModel());
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (CommandException e) {
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
            }
        });
    }

    /**
     * Toggle between enabling and disabling right-click operations on tag list. Note that when disabling, not only
     * the context menu is disabled, but right-click to select is also disabled.
     */
    public void toggleRightClick(boolean isEnabled) {
        if (isEnabled) {
            tagListView.removeEventFilter(MouseEvent.ANY, disableRightClick);
        } else {
            tagListView.addEventFilter(MouseEvent.ANY, disableRightClick);
            contextMenu.getItems().clear();
            contextMenu.hide();
        }
    }

    public ListView<Tag> getTagListView() {
        return tagListView;
    }
}
