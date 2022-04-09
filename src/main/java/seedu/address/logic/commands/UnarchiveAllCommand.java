package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_ONLY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class UnarchiveAllCommand extends Command {
    public static final String COMMAND_WORD = "unarchive_all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unarchives all entries in the displayed entry list.";

    public static final String MESSAGE_SUCCESS = "Unarchived all entries\n"
        + "Listing all unarchived entries.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entry unarchivedEntry = model.archiveEntry(0, false);

        for (int index = 1; unarchivedEntry != null; index++) {
            unarchivedEntry = model.archiveEntry(index, false);
        }

        // If the filteredList's predicate is UNARCHIVED_ONLY it will not update itself to remove the archived
        // entry, so we have to set it to a different predicate first.
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_ALL);
        model.updateCurrentlyDisplayedList(PREDICATE_SHOW_UNARCHIVED_ONLY);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof UnarchiveAllCommand; // instanceof handles nulls
    }
}
