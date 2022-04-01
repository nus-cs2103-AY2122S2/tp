package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class DeleteAllCommand extends Command {
    public static final String COMMAND_WORD = "delete_all";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all entries in the displayed entry list.";

    public static final String MESSAGE_DELETE_ALL_SUCCESS = "Deleted all entries.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Entry deletedEntry;

        do {
            deletedEntry = model.deleteEntry(1);
        } while (deletedEntry != null);

        return new CommandResult(MESSAGE_DELETE_ALL_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof DeleteCommand; // instanceof handles nulls
    }
}
