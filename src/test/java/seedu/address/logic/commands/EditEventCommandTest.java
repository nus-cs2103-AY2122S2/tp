package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertEventCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBookWithEvents;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditEventCommand.
 */
public class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        descriptor.setRemoveFriendNames(model.getFilteredEventList().get(0).getFriendNames());

        EditEventCommand editCommand = new EditEventCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertEventCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_EVENT_NAME).withDateTime(VALID_EVENT_DATETIME)
                .withDescription(VALID_EVENT_DESCRIPTION).build();

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME)
                .withDateTime(VALID_EVENT_DATETIME)
                .withDescription(VALID_EVENT_DESCRIPTION).build();
        EditEventCommand editCommand = new EditEventCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setEvent(lastEvent, editedEvent);

        assertEventCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        EditEventCommand editCommand = new EditEventCommand(INDEX_FIRST_PERSON, new EditEventCommand.EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertEventCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEvent_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editCommand = new EditEventCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME).build();
        EditEventCommand editCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_PERSON, DESC_A);

        // same values -> returns true
        EditEventCommand.EditEventDescriptor copyDescriptor = new EditEventCommand.EditEventDescriptor(DESC_A);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_PERSON, DESC_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_PERSON, DESC_B)));
    }


}
