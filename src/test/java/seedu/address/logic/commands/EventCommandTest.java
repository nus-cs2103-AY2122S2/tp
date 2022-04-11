package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Person;

public class EventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventCommand(null, null, null, null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EventCommand eventCommand = new EventCommand(new Index[]{Index.fromOneBased(1)}, new EventName("lunch"),
                new Information("at HDL"), new DateTime(2022, 10, 10, 11, 11));
        Event event = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));

        String expectedMessage = String.format(EventCommand.MESSAGE_ARGUMENTS,
                new ArrayList<>(List.of(person.getName())), event);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEvent(event);

        assertCommandSuccess(eventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EventCommand eventCommand = new EventCommand(new Index[]{Index.fromOneBased(1)}, new EventName("lunch"),
                new Information("at HDL"), new DateTime(2022, 10, 10, 11, 11));
        Event event = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addEvent(event);

        assertThrows(CommandException.class,
                EventCommand.MESSAGE_DUPLICATE_EVENT, () -> eventCommand.execute(expectedModel));
    }

}
