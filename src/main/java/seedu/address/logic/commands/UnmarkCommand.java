package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.procedure.Completion;
import seedu.address.model.procedure.Procedure;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark the procedure in the client as incomplete. \n"
            + "Parameters: CLIENT_INDEX, PROCEDURE_INDEX (must be both positive integers) \n"
            + "Example: unmark 1 1";

    public static final String MESSAGE_SUCCESS = "Procedure successfully unmarked.";

    private final Index targetClientIndex;
    private final Index targetProcedureIndex;

    /**
     * Creates an UnmarkCommand for a {@code Procedure} in a {@code Client}
     */
    public UnmarkCommand(Index targetClientIndex, Index targetProcedureIndex) {
        requireNonNull(targetClientIndex);
        requireNonNull(targetProcedureIndex);
        this.targetClientIndex = targetClientIndex;
        this.targetProcedureIndex = targetProcedureIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetClientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToShow = lastShownList.get(targetClientIndex.getZeroBased());
        List<Procedure> procedureList = clientToShow.getProcedures();
        if (targetProcedureIndex.getZeroBased() >= procedureList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROCEDURE_DISPLAYED_INDEX);
        }

        Procedure targetProcedure = procedureList.get(targetProcedureIndex.getZeroBased());
        targetProcedure.setHasCompleted(new Completion("false"));

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof UnmarkCommand) {
            UnmarkCommand otherCommand = (UnmarkCommand) other;
            return targetClientIndex.equals(otherCommand.targetClientIndex)
                    && targetProcedureIndex.equals(otherCommand.targetProcedureIndex);
        } else {
            return false;
        }
    }
}
