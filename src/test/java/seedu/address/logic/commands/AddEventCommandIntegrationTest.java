package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertEventCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalAddressBookWithEvents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TypicalEvents;

/**
 * Contains integration tests (interaction with the Model) for {@code AddEventCommand}.
 */
public class AddEventCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithEvents(), new UserPrefs());
    }

    @Test
    public void execute_newEvent_success() {
        Event validEvent = new EventBuilder().withNames(TypicalEvents.ALEX_NAME)
                .withDateTime("15-02-3030 1230").build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getUserPrefs());
        expectedModel.addEvent(validEvent);

        assertEventCommandSuccess(new AddEventCommand(validEvent), model,
                String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event eventInList = model.getAddressBook().getEventList().get(0);
        assertCommandFailure(new AddEventCommand(eventInList), model, AddEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidFriendNames_throwsCommandException() {
        Event invalidFriendNameEvent = new EventBuilder().withNames("invalid name").build();
        assertCommandFailure(new AddEventCommand(invalidFriendNameEvent),
                model, Messages.MESSAGE_INVALID_EVENT_FRIENDS);
    }

}
