package seedu.trackbeau.logic.commands.service;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.service.Service;


/**
 * Deletes service(s) identified using it's displayed index from trackBeau.
 */
public class DeleteServiceCommand extends Command {

    public static final String COMMAND_WORD = "deletes";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the service(s) identified by the index number(s) used in the displayed service list.\n"
        + "Parameters: INDEXES (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_DELETE_SERVICE_SUCCESS = "Deleted Service(s):\n%1$s";

    private final ArrayList<Index> targetIndexes;

    public DeleteServiceCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Service> lastShownList = model.getServiceList();

        ArrayList<Service> servicesToDelete = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
            }
            servicesToDelete.add(lastShownList.get(targetIndex.getZeroBased()));
        }

        StringBuilder sb = new StringBuilder();
        for (Service serviceToDelete : servicesToDelete) {
            // TODO: Delete future bookings related to these services

            model.deleteService(serviceToDelete);
            sb.append(serviceToDelete).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_SERVICE_SUCCESS, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteServiceCommand // instanceof handles nulls
            && targetIndexes.containsAll(((DeleteServiceCommand) other).targetIndexes)); // state check
    }
}
