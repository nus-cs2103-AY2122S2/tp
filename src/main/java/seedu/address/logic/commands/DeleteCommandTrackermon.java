package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.MessagesTrackermon;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelTrackermon;
import seedu.address.model.show.Show;

/**
 * Deletes a show identified using it's displayed index from Trackermon.
 */
public class DeleteCommandTrackermon extends CommandTrackermon {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the show identified by the index number used in the displayed Trackermon.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_SHOW_SUCCESS = "Deleted Show: %1$s";

    private final Index targetIndex;

    public DeleteCommandTrackermon(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResultTrackermon execute(ModelTrackermon model) throws CommandException {
        requireNonNull(model);
        List<Show> lastShownList = model.getFilteredShowList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MessagesTrackermon.MESSAGE_INVALID_SHOW_DISPLAYED_INDEX);
        }

        Show showToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteShow(showToDelete);
        return new CommandResultTrackermon(String.format(MESSAGE_DELETE_SHOW_SUCCESS, showToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommandTrackermon // instanceof handles nulls
                && targetIndex.equals(((DeleteCommandTrackermon) other).targetIndex)); // state check
    }
}
