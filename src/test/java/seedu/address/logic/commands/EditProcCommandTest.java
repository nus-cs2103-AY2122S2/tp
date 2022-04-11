package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROCEDURE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditProcCommand.EditProcedureDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Procedure;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditProcedureDescriptorBuilder;
import seedu.address.testutil.ProcedureBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditProcCommand.
 */
public class EditProcCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editProcedureFromExistingClient_success() {
        Procedure procedureToEdit = new ProcedureBuilder().withInfo(VALID_INFO_REPAIR)
                .withCost(VALID_COST_REPAIR).withDate(VALID_DATE_REPAIR).build();
        ArrayList<Procedure> clientProcedures = new ArrayList<>();
        clientProcedures.add(procedureToEdit);
        if (model.getFilteredClientList().size() < 1) {
            Client validClient = new ClientBuilder().withProcedures(clientProcedures).build();
            model.addClient(validClient);
        } else {
            Client editThisClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
            Client validClient = new ClientBuilder(editThisClient).withProcedures(clientProcedures).build();
            model.setClient(editThisClient, validClient);
        }

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new UserPrefs());
        Procedure newProcedure = new ProcedureBuilder().withInfo(VALID_INFO_REPLACE)
                .withCost(VALID_COST_REPLACE).withDate(VALID_DATE_REPLACE).build();
        EditProcedureDescriptor editProcedureDescriptor = new EditProcedureDescriptorBuilder(newProcedure)
                .build();
        EditProcCommand editProcCommand = new EditProcCommand(INDEX_FIRST_CLIENT,
                INDEX_FIRST_PROCEDURE, editProcedureDescriptor);

        Client targetClient = expectedModel.getFilteredClientList().get(0);
        ArrayList<Procedure> newProcedures = new ArrayList<>(targetClient.getProcedures());
        newProcedures.set(INDEX_FIRST_PROCEDURE.getZeroBased(), newProcedure);
        Client editedClient = new ClientBuilder(targetClient).withProcedures(newProcedures).build();
        expectedModel.setClient(targetClient, editedClient);
        String expectedMessage = String.format(EditProcCommand.MESSAGE_EDIT_PROCEDURE_SUCCESS, newProcedure,
                editedClient.getName(), editedClient.getAddress());
        assertCommandSuccess(editProcCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editProcedureFromNullClient_failure() {
        assertThrows(NullPointerException.class, () -> new EditProcCommand(null,
            INDEX_FIRST_PROCEDURE, new EditProcedureDescriptorBuilder(new ProcedureBuilder().build())
            .build()));
    }

    @Test
    public void execute_editProcedureFromNullProcedure_failure() {
        ArrayList<Procedure> clientProcedures = new ArrayList<>();
        Client newClient = new ClientBuilder().withProcedures(clientProcedures).build();
        if (model.getFilteredClientList().size() < 1) {
            model.addClient(newClient);
        } else {
            model.setClient(model.getFilteredClientList().get(0), newClient);
        }
        assertThrows(NullPointerException.class, () -> new EditProcCommand(null,
            INDEX_FIRST_PROCEDURE, new EditProcedureDescriptorBuilder(new ProcedureBuilder().build())
            .build()));
    }

    @Test
    public void execute_editProcedureFromNullDescriptor_failure() {
        Procedure newProcedure = new ProcedureBuilder().build();
        ArrayList<Procedure> clientProcedures = new ArrayList<>();
        clientProcedures.add(newProcedure);
        Client newClient = new ClientBuilder().withProcedures(clientProcedures).build();
        if (model.getFilteredClientList().size() < 1) {
            model.addClient(newClient);
        } else {
            model.setClient(model.getFilteredClientList().get(0), newClient);
        }

        assertThrows(NullPointerException.class, () -> new EditProcCommand(INDEX_FIRST_CLIENT,
            INDEX_FIRST_PROCEDURE, null));
    }
}
