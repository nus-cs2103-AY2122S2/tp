package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import seedu.address.model.Model;

/**
 * Lists all records in the address book to the user.
 */
public class ListRecordCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all records";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
