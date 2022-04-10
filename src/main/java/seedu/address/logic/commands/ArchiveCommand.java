package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_ONLY;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class ArchiveCommand extends Command {
    public static final String COMMAND_WORD = "archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the entry identified by the index number used in the displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_ENTRY_SUCCESS = "Archived Entry: %1$s";

    private final Index targetIndex;

    public ArchiveCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entry archivedEntry = model.archiveEntry(targetIndex.getZeroBased(), true);

        if (archivedEntry == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        // If the filteredList's predicate is UNARCHIVED_ONLY it will not update itself to remove the archived
        // entry, so we have to set it to a different predicate first.
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_UNARCHIVED_ONLY);

        return new CommandResult(String.format(MESSAGE_ARCHIVE_ENTRY_SUCCESS, archivedEntry));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex)); // state check
    }
}
