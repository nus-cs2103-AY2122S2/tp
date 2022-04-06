package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_FRIENDS;
import static seedu.address.logic.commands.AddEventCommand.MESSAGE_PAST_EVENT_WARNING;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ModelStub;

class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_pastEventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().withDateTime("01-12-1995 1230").build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);
        String expectedOutput = String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent)
                + "\n" + MESSAGE_PAST_EVENT_WARNING;

        assertEquals(expectedOutput, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_futureEventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEventAdded modelStub = new ModelStubAcceptingEventAdded();
        Event validEvent = new EventBuilder().withDateTime("12-05-3030 1459").build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.eventsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithEvent(validEvent);

        assertThrows(CommandException.class,
                AddEventCommand.MESSAGE_DUPLICATE_EVENT, () -> addEventCommand.execute(modelStub));
    }

    @Test
    public void execute_invalidEventFriends_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        ModelStubWithoutEventFriends modelStub = new ModelStubWithoutEventFriends();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);

        assertThrows(CommandException.class,
                MESSAGE_INVALID_EVENT_FRIENDS, () -> addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event validEvent = new EventBuilder().build();
        Event otherEvent = new EventBuilder().withName("other event").build();

        AddEventCommand addValidEventCommand = new AddEventCommand(validEvent);
        AddEventCommand addOtherEventCommand = new AddEventCommand(otherEvent);

        // same object -> returns true
        assertEquals(addValidEventCommand, addValidEventCommand);

        // same values -> returns true
        AddEventCommand addValidEventCommandCopy = new AddEventCommand(validEvent);
        assertEquals(addValidEventCommand, addValidEventCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addValidEventCommand);

        // null -> returns false
        assertNotEquals(null, addValidEventCommand);

        // different event -> returns false
        assertNotEquals(addValidEventCommand, addOtherEventCommand);
    }

    /**
     * A Model stub that contains a single event.
     */
    private static class ModelStubWithEvent extends ModelStub {
        private final Event event;

        ModelStubWithEvent(Event event) {
            requireNonNull(event);
            this.event = event;
        }

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return this.event.isSameEvent(event);
        }

        @Override
        public boolean areEventFriendsValid(Event event) {
            return true;
        }
    }

    /**
     * A Model stub without the correct event friends.
     */
    private static class ModelStubWithoutEventFriends extends ModelStub {
        @Override
        public boolean hasEvent(Event event) {
            return false;
        }

        @Override
        public boolean areEventFriendsValid(Event event) {
            return false;
        }
    }

    /**
     * A Model stub that always accepts the event being added.
     */
    private static class ModelStubAcceptingEventAdded extends ModelStub {
        final ArrayList<Event> eventsAdded = new ArrayList<>();

        @Override
        public boolean hasEvent(Event event) {
            requireNonNull(event);
            return eventsAdded.stream().anyMatch(event::isSameEvent);
        }

        @Override
        public void addEvent(Event event) {
            requireNonNull(event);
            eventsAdded.add(event);
        }

        @Override
        public boolean areEventFriendsValid(Event event) {
            return true;
        }
    }
}
