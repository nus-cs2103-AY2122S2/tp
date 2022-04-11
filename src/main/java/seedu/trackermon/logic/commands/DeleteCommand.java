package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.logic.commands.exceptions.CommandException;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Show;

/**
 * Deletes a show identified using it's displayed index from Trackermon.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_FORMAT = "Parameters: INDEX";
    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the show identified by the index number used in the displayed Trackermon.\n"
            + COMMAND_FORMAT + "\n" + COMMAND_EXAMPLE;

    public static final String MESSAGE_DELETE_SHOW_SUCCESS = "Deleted Show: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteCommand to delete the specified {@code Show}
     * @param targetIndex the index of the show to be deleted.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     * @throws CommandException if there is an invalid index.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Show> lastShownList = model.getFilteredShowList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Show showToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteShow(showToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SHOW_SUCCESS, showToDelete));
    }

    /**
     * Returns whether two objects are equal.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
