package seedu.unite.ui.general;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.util.Callback;
import seedu.unite.logic.Logic;
import seedu.unite.logic.commands.CommandResult;
import seedu.unite.logic.commands.DeleteTagCommand;
import seedu.unite.logic.commands.FilterCommand;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.UiManager;
import seedu.unite.ui.UiPart;

public class TagList extends UiPart<Region> {
    private static final String FXML = "TagList.fxml";
    private static Logic logic;
    private static UiManager uiManager;
    private static final ContextMenu contextMenu = new ContextMenu();
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
    public TagList(Logic logic, UiManager uiManager) {
        super(FXML);
        TagList.logic = logic;
        tagListView.addEventFilter(MouseEvent.ANY, disableRightClick);
        TagList.uiManager = uiManager;
    }

    public void setTagList(ObservableList<Tag> tagList) {
        tagListView.setItems(tagList);
        tagListView.setCellFactory(new TagListCellFactory());
        tagListView.setContextMenu(contextMenu);
    }

    public static class TagListCellFactory implements Callback<ListView<Tag>, ListCell<Tag>> {
        @Override
        public ListCell<Tag> call(ListView<Tag> param) {
            if (param.getItems().size() == 0) {
                contextMenu.getItems().clear();
                contextMenu.hide();
            }
            ListCell<Tag> cell = new TagListViewCell();
            cell.setOnMouseClicked((MouseEvent mouseEvent) -> {
                // if clicking on an empty area, do nothing
                if (cell.isEmpty()) {
                    mouseEvent.consume();
                    contextMenu.getItems().clear();
                    contextMenu.hide();
                    param.getSelectionModel().clearSelection();
                } else {
                    Parent p = (Parent) mouseEvent.getSource();
                    // Handles the event where the selected tag card changes. This handler will be triggered
                    // only on mouse clicked (primary or secondary).
                    int index = (Integer) p.getUserData();
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        handleSetFilteredList(param.getItems().get(index).getTagName());
                    } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        handleTagContextMenu(index + 1, param);
                    }
                }
            });
            return cell;
        }

        public static final class TagListViewCell extends ListCell<Tag> {
            @Override
            protected void updateItem(Tag tag, boolean empty) {
                super.updateItem(tag, empty);

                if (empty || tag == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new TagCard(tag, getIndex() + 1,
                            logic.getModel().countPersonsInTag(tag)).getRoot());
                    setUserData(getIndex());
                }
            }
        }
    }

    /**
     * Handles the event whenever the selected tag card changes.
     */
    public static void handleSetFilteredList(String tagName) {
        try {
            CommandResult commandResult = logic.execute(FilterCommand.COMMAND_WORD + " " + tagName);
            uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the event where user right-clicks on a tag card.
     */
    public static void handleTagContextMenu(int index, ListView<Tag> param) {
        contextMenu.getItems().clear();
        MenuItem delete = new MenuItem("Delete Tag");
        contextMenu.getItems().addAll(delete);
        delete.setOnAction(event -> {
            try {
                CommandResult commandResult = logic.execute(DeleteTagCommand.COMMAND_WORD + " " + index);
                // clear the tag list selection immediately after deletion
                param.getSelectionModel().clearSelection();
                uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (ParseException | CommandException e) {
                uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
            }
        });
    }

    /**
     * Handles the event whenever the selected tag card changes. This handler will be triggered only on arrow
     * key pressed, and will only be used to set the person list panel to the filtered list.
     */
    public void handleKeySelect(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
            Tag tag = tagListView.getSelectionModel().getSelectedItem();
            handleSetFilteredList(tag.getTagName());
        } else {
            tagListView.requestFocus();
        }
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
