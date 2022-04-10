package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.record.Record;

/**
 * Lists all records in the record book to the user.
 */
public class ListRecordCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all records";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Record> predicate = r -> r.getEndLocalDate().isAfter(LocalDate.now());
        model.updateFilteredRecordList(predicate);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true, false, false, false, false);
    }
}
