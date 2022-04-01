package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_ONLY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class ArchiveAllCommand extends Command {
    public static final String COMMAND_WORD = "archive_all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives all entries in the displayed entry list.";

    public static final String MESSAGE_ARCHIVE_ALL_SUCCESS = "Archived all entries";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entry archivedEntry;

        do {
            archivedEntry = model.archiveEntry(1, true);
        } while (archivedEntry != null);

        // If the filteredList's predicate is UNARCHIVED_ONLY it will not update itself to remove the archived
        // entry, so we have to set it to a different predicate first.
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_UNARCHIVED_ONLY);

        return new CommandResult(MESSAGE_ARCHIVE_ALL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ArchiveCommand; // instanceof handles nulls
    }
}
