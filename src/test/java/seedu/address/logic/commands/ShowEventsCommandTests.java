package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertEventCommandSuccess;
import static seedu.address.model.util.SampleDataUtil.getSampleAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;



/**
 * Contains integration tests (interaction with the Model) and unit tests for showfriends command.
 */
public class ShowEventsCommandTests {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    /**
     * Checks whether showevents executes properly.
     */
    @Test
    public void execute_showEvents_showsSameList() {
        expectedModel.updateFilteredEventList(event -> event.getDateTime().isAfterNow());
        assertEventCommandSuccess(new ShowEventsCommand(false), model, ShowEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    /**
     * Checks whether showevents executes properly.
     */
    @Test
    public void execute_showAllEvents_showsSameList() {
        expectedModel.updateFilteredEventList(event -> true);
        assertEventCommandSuccess(new ShowEventsCommand(true), model, ShowEventsCommand.MESSAGE_SUCCESS_ALL, expectedModel);
    }

    /**
     * If model has no events but persons showevents should have an empty list.
     */
    @Test
    public void execute_noevents_showsEmptyList() {
        Model noEventModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertEventCommandSuccess(new ShowEventsCommand(false), noEventModel, ShowEventsCommand.MESSAGE_SUCCESS, noEventModel);

        // Model has persons but no events, thus event list should be empty.
        assertFalse(noEventModel.getFilteredPersonList().isEmpty());
        assertTrue(noEventModel.getFilteredEventList().isEmpty());
    }

    /**
     * Model's internal list should be sorted.
     */
    @Test
    public void execute_listIsFiltered_showsOrderedList() {
        expectedModel.updateFilteredEventList(event -> true);
        assertEventCommandSuccess(new ShowEventsCommand(true), model, ShowEventsCommand.MESSAGE_SUCCESS_ALL, expectedModel);

        ObservableList<Event> checkSorted = model.getFilteredEventList();

        Event temp = checkSorted.get(0);
        for (int i = 1; i < checkSorted.size(); i++) {
            assertTrue(temp.compareTo(checkSorted.get(i)) < 0);
            temp = checkSorted.get(0);
        }
    }
}
