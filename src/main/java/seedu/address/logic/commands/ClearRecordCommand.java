package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.RecordBook;

/**
 * Clears the record list.
 */
public class ClearRecordCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Record book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRecordBook(new RecordBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
