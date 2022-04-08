package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.parser.ArgumentMultimap;
import seedu.contax.logic.parser.Prefix;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RangeCommand.
 */
public class RangeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void equals() {
        String sampleCommand = "range edit from/1 to/3 p/12345678";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        RangeCommand rangeCommand =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        RangeCommand rangeCommand2 =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), "sampleCommand");

        // same object -> returns true
        assertTrue(rangeCommand.equals(rangeCommand));

        // same values -> returns true
        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(3), sampleCommand);
        assertTrue(rangeCommand.equals(rangeCommandCopy));

        // different types -> returns false
        assertFalse(rangeCommand.equals(1));

        // null -> returns false
        assertFalse(rangeCommand.equals(null));

        // different person -> returns false
        assertFalse(rangeCommand.equals(rangeCommand2));
    }

    @Test
    public void execute_personAcceptedByModel_rangeEditSuccessful() throws Exception {
        String sampleCommand = "editperson p/123";
        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(1), sampleCommand);
        CommandResult commandResult = new RangeCommand(Index.fromZeroBased(1), Index.fromZeroBased(1),
                sampleCommand).execute(model);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new Schedule(), new UserPrefs());
        assertCommandSuccess(rangeCommandCopy, model, commandResult, expectedModel);
    }

    @Test
    public void execute_personAcceptedByModel_rangeDeleteSuccessful() throws Exception {
        String sampleCommand = "deleteperson";
        Model modelCopy = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        RangeCommand rangeCommandCopy =
                new RangeCommand(Index.fromZeroBased(0), Index.fromZeroBased(5), sampleCommand);
        CommandResult commandResult = new RangeCommand(Index.fromZeroBased(0), Index.fromZeroBased(5),
                sampleCommand).execute(model);
        AddressBook expectedAddressBook = getTypicalAddressBook();
        expectedAddressBook.setPersons(List.of(modelCopy.getAddressBook().getPersonList().get(6)));
        Model expectedModel = new ModelManager(expectedAddressBook,
                new Schedule(), new UserPrefs());
        assertCommandSuccess(rangeCommandCopy, modelCopy, commandResult, expectedModel);
    }

    @Test
    public void execute_personAcceptedByModel_invalidIndex() throws Exception {
        String sampleCommand = "deleteperson from/2 to/1";
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix(""), "delete");
        argumentMultimap.put(new Prefix("/from"), "2");
        argumentMultimap.put(new Prefix("/to"), "1");
        RangeCommand rangeCommand =
                new RangeCommand(Index.fromZeroBased(2), Index.fromZeroBased(1), sampleCommand);
        assertCommandFailure(rangeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
