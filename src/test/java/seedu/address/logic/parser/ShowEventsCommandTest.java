package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_PAST_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_EVENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ShowEventsCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Person;

public class ShowEventsCommandTest {
    private Model model;
    private Event eventPast;
    private Event eventUpcoming;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        eventPast = new Event(new EventName("lunch"), new Information("at HDL"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2020, 10, 10, 11, 11));
        eventUpcoming = new Event(new EventName("dinner"), new Information("at Lavo"),
                new ArrayList<>(List.of(person.getName())), new DateTime(2220, 10, 10, 11, 11));
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_showAllEvents_success() {
        ShowEventsCommand command = new ShowEventsCommand(PREDICATE_SHOW_ALL_EVENTS);

        model.addEvent(eventPast);
        model.addEvent(eventUpcoming);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW,
                expectedModel.getFilteredEventList().size());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_showPastEvents_success() {
        ShowEventsCommand command = new ShowEventsCommand(PREDICATE_SHOW_PAST_EVENTS);

        model.addEvent(eventUpcoming);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 0);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredEventList().isEmpty());
    }

    @Test
    public void execute_showUpcomingEvents_success() {
        ShowEventsCommand command = new ShowEventsCommand(PREDICATE_SHOW_UPCOMING_EVENTS);

        model.addEvent(eventUpcoming);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 1);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredEventList().size() == 1);
    }


}

