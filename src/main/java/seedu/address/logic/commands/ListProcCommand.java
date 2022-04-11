package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_CLIENT_PROCEDURES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;

public class ListProcCommand extends Command {

    public static final String COMMAND_WORD = "listProc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists the procedures of a client in the index. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: listProc 1";

    public static final String MESSAGE_SUCCESS = "Procedures successfully loaded.";

    public static final String MESSAGE_FAIL = "No Procedures to load!";

    private final Index targetIndex;

    /**
     * Creates an ListProcCommand to add the specified {@code Client}
     */
    public ListProcCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }
        Client clientToShow = lastShownList.get(targetIndex.getZeroBased());
        if (clientToShow.getProcedures().isEmpty()) {
            model.setProcedures(clientToShow.getProcedures());
            model.updateFilteredProcedureList(clientToShow, PREDICATE_SHOW_CLIENT_PROCEDURES);
            return new CommandResult(MESSAGE_FAIL);
        } else {
            model.setProcedures(clientToShow.getProcedures());
            model.updateFilteredProcedureList(clientToShow, PREDICATE_SHOW_CLIENT_PROCEDURES);
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListProcCommand // instanceof handles nulls
                && targetIndex.equals(((ListProcCommand) other).targetIndex));
    }
}
