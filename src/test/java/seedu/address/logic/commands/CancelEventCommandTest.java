package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Person;

public class CancelEventCommandTest {

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
    public void execute_validIndexUnfilteredList_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event eventToDelete = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        CancelEventCommand cancelEventCommand = new CancelEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(CancelEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        model.addEvent(eventToDelete);

        assertCommandSuccess(cancelEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event eventToDelete = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        model.addEvent(eventToDelete);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        CancelEventCommand cancelEventCommand = new CancelEventCommand(outOfBoundIndex);

        assertCommandFailure(cancelEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event event = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        model.addEvent(event);
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventToDelete = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        CancelEventCommand cancelEventCommand = new CancelEventCommand(INDEX_FIRST_EVENT);

        String expectedMessage = String.format(CancelEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(cancelEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListMultipleOutOfOrder_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event eventToDelete1 = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        Event eventToDelete2 = new Event(new EventName("dinner"), new Information("at Bread Street Kitchen"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        model.addEvent(eventToDelete1);
        model.addEvent(eventToDelete2);
        StringBuilder eventsToDelete = new StringBuilder();
        eventsToDelete.append(eventToDelete1)
                .append(System.lineSeparator()).append(eventToDelete2);

        CancelEventCommand cancelEventCommand = new CancelEventCommand(
                new Index[]{INDEX_SECOND_PERSON, INDEX_FIRST_PERSON});

        String expectedMessage = String.format(CancelEventCommand.MESSAGE_DELETE_EVENTS_SUCCESS, eventsToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete1);
        expectedModel.deleteEvent(eventToDelete2);

        assertCommandSuccess(cancelEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListMultipleInOrder_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Event eventToDelete1 = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        Event eventToDelete2 = new Event(new EventName("dinner"), new Information("at Bread Street Kitchen"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2022, 10, 10, 11, 11));
        model.addEvent(eventToDelete1);
        model.addEvent(eventToDelete2);
        StringBuilder eventsToDelete = new StringBuilder();
        eventsToDelete.append(eventToDelete2)
                .append(System.lineSeparator()).append(eventToDelete1);

        CancelEventCommand cancelEventCommand = new CancelEventCommand(
                new Index[]{INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});

        String expectedMessage = String.format(CancelEventCommand.MESSAGE_DELETE_EVENTS_SUCCESS, eventsToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteEvent(eventToDelete1);
        expectedModel.deleteEvent(eventToDelete2);

        assertCommandSuccess(cancelEventCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }
}
