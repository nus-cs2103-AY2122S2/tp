package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_CLIENT_PROCEDURES;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Plan;
import seedu.address.model.procedure.Procedure;
import seedu.address.model.tag.Tag;

/**
 * Deletes an existing procedure that belongs to an existing client in the address book.
 */
public class DeleteProcCommand extends Command {

    public static final String COMMAND_WORD = "deleteProc";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the Procedure under a Client "
            + "by the index numbers used in the Client Panel and the Procedure Panel. "
            + "Existing Procedures indicated (by the input value) will be deleted.\n"
            + "Parameters: INDEX (must be a positive integer and an existing Client)\n"
            + "INDEX (must be a positive integer and an existing Procedure)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_PROCEDURE_SUCCESS = "Deleted Procedure: %1$s";
    public static final String MESSAGE_DELETE_PROCEDURE_FAILURE = "Deleted Procedure unsuccessful."
            + "\nTry again in a while!";

    private final Index clientIndex;
    private final Index procedureIndex;

    /**
     * @param clientIndex of the client in the filtered client list, whose procedure you'd like to delete.
     * @param procedureIndex of the procedure in the filtered procedure list to delete
     */
    public DeleteProcCommand(Index clientIndex, Index procedureIndex) {
        requireNonNull(clientIndex);
        requireNonNull(procedureIndex);

        this.clientIndex = clientIndex;
        this.procedureIndex = procedureIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (clientIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_NON_POSITIVE_INDEX);
        }
        List<Client> lastShownList = model.getFilteredClientList();

        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(clientIndex.getZeroBased());
        Client editedClient = null;
        List<Procedure> clientProcedureList = clientToEdit.getProcedures();
        List<Procedure> procedureDeletedList = new ArrayList<>();

        try {
            procedureDeletedList = deleteProcedureFromList(clientProcedureList, procedureIndex);
        } catch (UnsupportedOperationException err) {
            throw new CommandException(DeleteProcCommand.MESSAGE_DELETE_PROCEDURE_FAILURE);
        }

        Procedure procedureDeleted = clientProcedureList.get(procedureIndex.getZeroBased());
        editedClient = updateClientProcedures(clientToEdit, procedureDeletedList);
        model.setClient(clientToEdit, editedClient);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        model.updateFilteredProcedureList(editedClient, PREDICATE_SHOW_CLIENT_PROCEDURES);
        return new CommandResult(String.format(MESSAGE_DELETE_PROCEDURE_SUCCESS, procedureDeleted));
    }

    /**
     * Creates and returns a {@code Client} with their {@code Procedure} updated by
     * {@code updatedProcedureList}.
     */
    private Client updateClientProcedures(Client clientToEdit,
            List<Procedure> updatedProcedureList) {
        assert clientToEdit != null && updatedProcedureList != null;

        Name updatedName = clientToEdit.getName();
        Phone updatedPhone = clientToEdit.getPhone();
        Email updatedEmail = clientToEdit.getEmail();
        Plan updatedPlan = clientToEdit.getPlan();
        Address updatedAddress = clientToEdit.getAddress();
        Set<Tag> updatedTags = clientToEdit.getTags();
        List<Procedure> updatedProcedures = new ArrayList<>(updatedProcedureList);
        return new Client(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedPlan,
                updatedTags, updatedProcedures);
    }

    /**
     * Creates and returns a list of {@code Procedure} with the intended {@code Procedure} at
     * {@code procedureIndex} deleted.
     */
    private List<Procedure> deleteProcedureFromList(List<Procedure> procedureList, Index procedureIndex)
            throws CommandException {
        if (procedureIndex.getOneBased() > procedureList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROCEDURE_DISPLAYED_INDEX);
        }
        if (procedureIndex.getOneBased() <= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROCEDURE_NON_POSITIVE_INDEX);
        }

        List<Procedure> updatedProcedureList = new ArrayList<>(procedureList);
        updatedProcedureList.remove(procedureIndex.getZeroBased());
        return updatedProcedureList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteProcCommand)) {
            return false;
        }

        // state check
        DeleteProcCommand e = (DeleteProcCommand) other;
        return clientIndex.equals(e.clientIndex)
                && procedureIndex.equals(e.procedureIndex);
    }
}
