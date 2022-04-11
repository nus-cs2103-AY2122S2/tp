package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROCEDURE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROCEDURE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ProcedureBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class DeleteProcCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_procedureDeletedFromExistingClient_success() {
        Procedure validProcedure = new ProcedureBuilder().build();
        ArrayList<Procedure> validProcedureList = new ArrayList<>();
        validProcedureList.add(validProcedure);

        Client clientToEdit = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client validClient = new ClientBuilder(clientToEdit).withProcedures(validProcedureList).build();
        model.setClient(clientToEdit, validClient);
        DeleteProcCommand deleteProcCommand = new DeleteProcCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_PROCEDURE);

        String expectedMessage = String.format(DeleteProcCommand
                .MESSAGE_DELETE_PROCEDURE_SUCCESS, validProcedure);

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(deleteProcCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_procedureDeletedFromNullClient_failure() {
        assertThrows(NullPointerException.class, () -> new DeleteProcCommand(null, INDEX_FIRST_PROCEDURE));
    }

    @Test
    public void execute_procedureDeletedFromNonExistingClient_failure() {
        Index clientListSize = Index.fromZeroBased(model.getFilteredClientList().size()); // Out of index
        DeleteProcCommand deleteProcCommand = new DeleteProcCommand(clientListSize, INDEX_FIRST_PROCEDURE);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);

        assertCommandFailure(deleteProcCommand, model, expectedMessage);
    }

    @Test
    public void equal() {
        DeleteProcCommand deleteProc1 = new DeleteProcCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_PROCEDURE);
        assertTrue(deleteProc1.equals(deleteProc1)); //Same instance

        DeleteProcCommand deleteProc2 = new DeleteProcCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_PROCEDURE);
        assertTrue(deleteProc1.equals(deleteProc2)); //Different instance, same values

        DeleteProcCommand deleteProc3 = new DeleteProcCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_PROCEDURE);
        assertFalse(deleteProc1.equals(deleteProc3)); //Different Client Index, Same Procedure Index
        assertFalse(deleteProc2.equals(deleteProc3)); //Different Client Index, Same Procedure Index

        DeleteProcCommand deleteProc4 = new DeleteProcCommand(INDEX_FIRST_CLIENT, INDEX_SECOND_PROCEDURE);
        assertFalse(deleteProc1.equals(deleteProc4)); //Same Client Index, Different Procedure Index
        assertFalse(deleteProc2.equals(deleteProc4)); //Same Client Index, Different Procedure Index

        assertFalse(deleteProc1.equals(null)); //Test against null values
    }
}

