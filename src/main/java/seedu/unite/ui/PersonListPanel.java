package seedu.unite.ui;

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
import seedu.unite.logic.commands.DeleteCommand;
import seedu.unite.logic.commands.ProfileCommand;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.person.Person;
import seedu.unite.ui.general.GeneralDisplay;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private static Logic logic;
    private static UiManager uiManager;
    private static final ContextMenu contextMenu = new ContextMenu();
    private final javafx.event.EventHandler<MouseEvent> disableRightClick = mouseEvent -> {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            mouseEvent.consume();
        }
    };

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, Logic logic, UiManager uiManager) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(new PersonListCellFactory());
        personListView.setContextMenu(contextMenu);
        PersonListPanel.logic = logic;
        PersonListPanel.uiManager = uiManager;
        personListView.addEventFilter(MouseEvent.ANY, disableRightClick);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard} with
     * customized {@code Callback}.
     */
    public static class PersonListCellFactory implements Callback<ListView<Person>, ListCell<Person>> {
        @Override
        public ListCell<Person> call(ListView<Person> param) {
            if (param.getItems().size() == 0) {
                contextMenu.getItems().clear();
                contextMenu.hide();
            }
            ListCell<Person> cell = new PersonListViewCell();
            cell.setOnMouseClicked((MouseEvent mouseEvent) -> {
                // if clicking on an empty area, do nothing
                if (cell.isEmpty()) {
                    mouseEvent.consume();
                    contextMenu.getItems().clear();
                    contextMenu.hide();
                    param.getSelectionModel().clearSelection();
                } else {
                    Parent p = (Parent) mouseEvent.getSource();
                    // Handles the event where the selected person card changes. This handler will be triggered
                    // only on mouse clicked (primary or secondary).
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        handleSetProfile((Integer) p.getUserData());
                    } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                        handleContextMenu((Integer) p.getUserData(), param);
                    }
                }
            });
            return cell;
        }

        public static final class PersonListViewCell extends ListCell<Person> {
            @Override
            protected void updateItem(Person person, boolean empty) {
                super.updateItem(person, empty);

                if (empty || person == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                    setUserData(getIndex() + 1);
                }
            }
        }
    }

    /**
     * Handles the event where the user left-clicks on a person card to show profile in general display.
     */
    public static void handleSetProfile(int index) {
        try {
            CommandResult commandResult = logic.execute(ProfileCommand.COMMAND_WORD + " " + index);
            uiManager.getMainWindow().getGeneralDisplay().setProfile(logic.getModel().getPerson());
            uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
        } catch (ParseException | CommandException e) {
            uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the event where the user right-clicks on a person card to delete.
     */
    public static void handleContextMenu(int index, ListView<Person> param) {
        contextMenu.getItems().clear();
        MenuItem delete = new MenuItem("Delete");
        contextMenu.getItems().addAll(delete);
        delete.setOnAction(event -> {
            try {
                CommandResult commandResult = logic.execute(DeleteCommand.COMMAND_WORD + " " + index);
                Person personToDelete = logic.getModel().getPerson();
                // clear the person list selection immediately after deletion
                param.getSelectionModel().clearSelection();
                // if the current profile is displaying the person being deleted, the profile will reset
                GeneralDisplay generalDisplay = uiManager.getMainWindow().getGeneralDisplay();
                Person currentPersonInProfile = generalDisplay.getProfile().getPerson();
                if (generalDisplay.getProfileDisplayPlaceholder().isVisible()
                        && currentPersonInProfile.isSamePerson(personToDelete)) {
                    uiManager.getMainWindow().getGeneralDisplay().resetProfile();
                } else if (currentPersonInProfile != null && !currentPersonInProfile.isSamePerson(personToDelete)) {
                    // otherwise, select the person that is currently being displayed in profile.
                    param.getSelectionModel().select(currentPersonInProfile);
                }
                // if tag list is being displayed, update the tag list accordingly.
                if (generalDisplay.getTagListPlaceholder().isVisible()) {
                    generalDisplay.getTagList().getTagListView().refresh();
                }
                uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(commandResult.getFeedbackToUser());
            } catch (ParseException | CommandException e) {
                uiManager.getMainWindow().getResultDisplay().setFeedbackToUser(e.getMessage());
            }
        });
    }

    /**
     * Handles the event whenever the selected person card changes. This handler will be triggered only on arrow
     * key pressed, and will only be used to set general display to the selected person's profile.
     */
    public void handleKeySelect(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
            int index = personListView.getSelectionModel().getSelectedIndex();
            handleSetProfile(index);
        } else {
            personListView.requestFocus();
        }
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
