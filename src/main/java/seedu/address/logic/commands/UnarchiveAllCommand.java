package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_ONLY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class UnarchiveAllCommand extends Command {
    public static final String COMMAND_WORD = "unarchive_all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives all entries in the displayed entry list.";

    public static final String MESSAGE_UNARCHIVE_ALL_SUCCESS = "Unarchived all entries.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entry unarchivedEntry;

        do {
            unarchivedEntry = model.archiveEntry(1, false);
        } while (unarchivedEntry != null);

        // If the filteredList's predicate is UNARCHIVED_ONLY it will not update itself to remove the archived
        // entry, so we have to set it to a different predicate first.
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_ARCHIVED_ONLY);

        return new CommandResult(MESSAGE_UNARCHIVE_ALL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof UnarchiveAllCommand; // instanceof handles nulls
    }
}
