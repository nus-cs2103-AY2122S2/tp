package seedu.address.ui;

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
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ProfileCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logic logic;
    private final ContextMenu contextMenu = new ContextMenu();
    private final javafx.event.EventHandler<MouseEvent> disableRightClick = event -> {
        if (event.getButton() == MouseButton.SECONDARY) {
            event.consume();
        }
    };

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, Logic logic) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setContextMenu(contextMenu);
        this.logic = logic;
        personListView.addEventFilter(MouseEvent.ANY, disableRightClick);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * The handler to set general display to the selected person' profile.
     */
    public void handleSetProfile() {
        try {
            if (personListView.getSelectionModel().getSelectedItem() != null) {
                Index personIndexSelected = Index.fromZeroBased(personListView.getSelectionModel().getSelectedIndex());
                ProfileCommand profileCommand = new ProfileCommand(personIndexSelected);
                CommandResult commandResult = profileCommand.execute(logic.getModel());
                UiManager.getMainWindow().getGeneralDisplay().setProfile(commandResult.getPerson());
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            }
        } catch (CommandException e) {
            UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the event whenever the selected person card changes. This handler will be triggered only on
     * mouse clicked (primary or secondary).
     */
    public void handleMouseSelect(MouseEvent mouseEvent) {
        // If primary key on mouse (left) is clicked, change the general display to profile of the selected person.
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            handleSetProfile();
        }
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            // If secondary key on mouse (right) is clicked, trigger context menu handler.
            handleContextMenu();
        }
    }

    /**
     * Handles the event whenever the selected person card changes. This handler will be triggered only on arrow
     * key pressed, and will only be used to set general display to the selected person's profile.
     */
    public void handleKeySelect(KeyEvent keyEvent) {
        if (personListView.getSelectionModel().getSelectedItem() != null) {
            if (keyEvent.getCode().isArrowKey()) {
                personListView.requestFocus();
                handleSetProfile();
            }
        }
    }

    /**
     * Handles the event where user right-clicks on a person card.
     */
    public void handleContextMenu() {
        contextMenu.getItems().clear();
        MenuItem delete = new MenuItem("Delete");
        contextMenu.getItems().addAll(delete);
        delete.setOnAction(event -> {
            try {
                Index personToDeleteIndex = Index.fromZeroBased(personListView.getSelectionModel().getSelectedIndex());
                DeleteCommand deleteCommand = new DeleteCommand(personToDeleteIndex);
                CommandResult commandResult = deleteCommand.execute(logic.getModel());
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (CommandException e) {
                UiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
            }
        });
    }

    /**
     * Toggle between enabling and disabling right-click operations on person list panel. Note that when disabling,
     * not only the context menu is disabled, but right-click to select is also disabled.
     */
    public void toggleRightClick(boolean isEnabled) {
        if (isEnabled) {
            personListView.removeEventFilter(MouseEvent.ANY, disableRightClick);
        } else {
            personListView.addEventFilter(MouseEvent.ANY, disableRightClick);
            contextMenu.getItems().clear();
            contextMenu.hide();
        }
    }

    /**
     * Gets person list view.
     *
     * @return the person list view
     */
    public ListView<Person> getPersonListView() {
        return this.personListView;
    }
}
