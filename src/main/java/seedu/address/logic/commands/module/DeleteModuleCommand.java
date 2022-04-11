package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Deletes a module identified using its displayed index from LinkyTime.
 */
public class DeleteModuleCommand extends Command {
    public static final String COMMAND_WORD = "mdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the module identified by the index number used in the displayed module list.\n"
            + "The module must not have any associated upcoming or expired meetings.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Note: Add the 'f/' flag to forcibly delete the module and all associated meetings.";

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Deleted Module: %1$s";

    public static final String MESSAGE_DELETE_MODULE_RESTRICTED = "Unable to delete Module: %1$s\n"
            + "There are meetings assigned to this module.\n";

    private final Index targetIndex;
    private final boolean isForced;

    /**
     * Creates a new {@code DeleteModuleCommand}.
     *
     * @param targetIndex Index of module to be deleted.
     * @param isForced    To indicate if the deletion is forced.
     */
    public DeleteModuleCommand(Index targetIndex, boolean isForced) {
        this.targetIndex = targetIndex;
        this.isForced = isForced;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        final List<Module> lastShownModuleList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        final Module moduleToDelete = lastShownModuleList.get(targetIndex.getZeroBased());
        final List<Meeting> allMeetings = model.getMeetingList();

        if (!isForced && hasAssociatedMeetings(allMeetings, moduleToDelete)) {
            throw new CommandException(String.format(MESSAGE_DELETE_MODULE_RESTRICTED, moduleToDelete));
        }

        if (isForced) {
            deleteAssociatedMeetings(model, moduleToDelete);
        }

        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteModuleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteModuleCommand) other).targetIndex)
                && isForced == ((DeleteModuleCommand) other).isForced); // state check
    }

    /**
     * Checks if the given module has meetings assigned to it.
     *
     * @param meetings The list of meetings to check.
     * @param module   The module to search for.
     * @return True, if there are associated meetings in the list.
     */
    private boolean hasAssociatedMeetings(List<Meeting> meetings, Module module) {
        return meetings.stream().anyMatch(meeting -> meeting.getModule().equals(module));
    }

    /**
     * Deletes all meetings associated with the given module.
     *
     * @param model  The model containing the list of meetings to check.
     * @param module The module to check against.
     */
    private void deleteAssociatedMeetings(Model model, Module module) {
        final List<Meeting> list = model.getMeetingList().filtered((meeting) -> meeting.getModule().equals(module));
        final ArrayList<Meeting> arr = new ArrayList<>(list);
        for (final Meeting m : arr) {
            model.deleteMeeting(m);
        }
    }
}
