package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String LIST_COMMAND = "listTransaction";
    private static final String FIND_COMMAND = "findTransaction %1$d";
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList} and command executor
     */
    public PersonListPanel(ObservableList<Person> personList, CommandExecutor commandExecutor) {
        super(FXML);
        MultipleSelectionModel<Person> selectionModel = personListView.getSelectionModel();
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> {
            PersonListViewCell cell = new PersonListViewCell();
            cell.setCursor(Cursor.HAND);
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                personListView.requestFocus();
                if (event.isPrimaryButtonDown() && !cell.isEmpty()) {
                    int index = cell.getIndex();
                    try {
                        if (selectionModel.getSelectedIndex() == index) {
                            selectionModel.clearSelection();
                            commandExecutor.execute(LIST_COMMAND);
                        } else {
                            selectionModel.select(index);
                            commandExecutor.execute(String.format(FIND_COMMAND, index + 1));
                        }
                    } catch (CommandException | ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                event.consume();
            });
            return cell;
        });
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
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
