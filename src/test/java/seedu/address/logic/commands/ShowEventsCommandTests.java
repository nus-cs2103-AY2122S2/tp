package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for showfriends command.
 */
public class ShowEventsCommandTests {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_showfriends_showsSameList() {
        assertCommandSuccess(new ShowEventsCommand(), model, ShowEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_noevents() {
        Model noEventModel = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());
        assertCommandSuccess(new ShowEventsCommand(), noEventModel, ShowEventsCommand.MESSAGE_SUCCESS, noEventModel);

        // Model has persons but no events, thus event list should be empty.
        assertFalse(noEventModel.getFilteredPersonList().isEmpty());
        assertTrue(noEventModel.getEventsList().isEmpty());
    }

    @Test
    public void execute_noPeople_noEvents() {
        Model PersonEventModel = new ModelManager(getTypicalAddressBook(), new EventBook(), new UserPrefs());
        Set<Person> tempSet = new HashSet<>(PersonEventModel.getFilteredPersonList());
        Event temp = new Event(new Name("RandomName"), new DateTime("10-10-12 1430"), new Description("blah"), tempSet);
        PersonEventModel.addEvent(temp);
        expectedModel.addEvent(temp);

        // Should display the same Events list.
        assertCommandSuccess(new ShowEventsCommand(), PersonEventModel, ShowEventsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
