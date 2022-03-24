package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.util.SearchType;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BatchCommand.
 */
public class BatchCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void equals() {
        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/321",
                        new SearchType(SearchType.TYPE_PHONE), "123", "", "");
        BatchCommand expectedBatchCommand2 =
                new BatchCommand("editperson p/321",
                        new SearchType(SearchType.TYPE_PHONE), "321", "", "");

        // same object -> returns true
        assertTrue(expectedBatchCommand.equals(expectedBatchCommand));

        // same values -> returns true

        BatchCommand expectedBatchCommandCopy =
                new BatchCommand("editperson p/321",
                        new SearchType(SearchType.TYPE_PHONE), "123", "", "");
        assertTrue(expectedBatchCommand.equals(expectedBatchCommandCopy));

        // different types -> returns false
        assertFalse(expectedBatchCommand.equals(1));

        // null -> returns false
        assertFalse(expectedBatchCommand.equals(null));

        // different command -> returns false
        assertFalse(expectedBatchCommand.equals(expectedBatchCommand2));
    }

    @Test
    public void execute_batchEditWithDifferentTypeEquals_success() throws Exception {
        String sampleCommand = "batch editperson p/12345678 by/phone =/9 ";

        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_PHONE), "94351253", "", "");
        CommandResult commandResult = expectedBatchCommand.execute(model);
        assertTrue(commandResult.getFeedbackToUser().equals(
                "Edited Person: Alice Pauline; Phone: 12345678; "
                        + "Email: alice@example.com; Address: 123, Jurong West Ave 6, #08-111; Tags: [friends]\n"));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_ADDRESS), "1234567890", "", "");
        CommandResult commandResult2 = expectedBatchCommand.execute(model);
        assertTrue(commandResult2.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_EMAIL), "1234567890", "", "");
        CommandResult commandResult3 = expectedBatchCommand.execute(model);
        assertTrue(commandResult3.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_NAME), "1234567890", "", "");
        CommandResult commandResult4 = expectedBatchCommand.execute(model);
        assertTrue(commandResult4.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
    }

    @Test
    public void execute_batchEditWithDifferentTypeStart_success() throws Exception {
        String sampleCommand = "batch editperson p/12345678 by/phone =/9 ";

        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_PHONE), "", "94351253", "");
        CommandResult commandResult = expectedBatchCommand.execute(model);
        assertTrue(commandResult.getFeedbackToUser().equals(
                "Edited Person: Alice Pauline; Phone: 12345678; "
                        + "Email: alice@example.com; Address: 123, Jurong West Ave 6, #08-111; Tags: [friends]\n"));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_ADDRESS), "", "1234567890", "");
        CommandResult commandResult2 = expectedBatchCommand.execute(model);
        assertTrue(commandResult2.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_EMAIL), "", "1234567890", "");
        CommandResult commandResult3 = expectedBatchCommand.execute(model);
        assertTrue(commandResult3.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_NAME), "", "1234567890", "");
        CommandResult commandResult4 = expectedBatchCommand.execute(model);
        assertTrue(commandResult4.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
    }

    @Test
    public void execute_batchEditWithDifferentTypeEnd_success() throws Exception {
        String sampleCommand = "batch editperson p/12345678 by/phone =/9 ";

        BatchCommand expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_PHONE), "", "", "94351253");
        CommandResult commandResult = expectedBatchCommand.execute(model);
        assertTrue(commandResult.getFeedbackToUser().equals(
                "Edited Person: Alice Pauline; Phone: 12345678; "
                        + "Email: alice@example.com; Address: 123, Jurong West Ave 6, #08-111; Tags: [friends]\n"));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_ADDRESS), "", "", "1234567890");
        CommandResult commandResult2 = expectedBatchCommand.execute(model);
        assertTrue(commandResult2.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_EMAIL), "", "", "1234567890");
        CommandResult commandResult3 = expectedBatchCommand.execute(model);
        assertTrue(commandResult3.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
        expectedBatchCommand =
                new BatchCommand("editperson p/12345678",
                        new SearchType(SearchType.TYPE_NAME), "", "", "1234567890");
        CommandResult commandResult4 = expectedBatchCommand.execute(model);
        assertTrue(commandResult4.getFeedbackToUser().equals("batch: No result matching \"1234567890\""));
    }

    @Test
    public void execute_parseAndCreateNewCommandInvalidValue_throwsCommandException() throws CommandException {
        BatchCommand expectedBatchCommand =
                new BatchCommand("",
                        new SearchType(SearchType.TYPE_PHONE), "94351253", "", "");
        assertThrows(CommandException.class, () -> expectedBatchCommand.execute(model));
    }

    @Test
    public void execute_parseMissingField_throwsCommandException() throws CommandException {
        BatchCommand expectedBatchCommand =
                new BatchCommand("",
                        new SearchType(SearchType.TYPE_PHONE), "", "", "");
        assertThrows(CommandException.class, () -> expectedBatchCommand.execute(model));
    }

    @Test
    public void execute_inValidCommand_throwsCommandException() throws CommandException {
        BatchCommand expectedBatchCommand =
                new BatchCommand("sample invalid",
                        new SearchType(SearchType.TYPE_PHONE), "", "", "");
        assertThrows(CommandException.class, () -> expectedBatchCommand.execute(model));
    }

}
