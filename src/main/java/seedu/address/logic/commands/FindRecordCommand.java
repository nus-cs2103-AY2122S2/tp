package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

/**
 * Finds and lists all records in record book whose insurance type contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRecordCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -r : Finds all records whose insurance type contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " -r health";

    private final RecordContainsKeywordsPredicate predicate;

    public FindRecordCommand(RecordContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecordList((predicate));
        String message = Messages.MESSAGE_RECORDS_LISTED_OVERVIEW;
        String resultMessage = String.format(message, model.getFilteredRecordList().size());
        return new CommandResult(resultMessage, false, false, false,
                true, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRecordCommand // instanceof handles nulls
                && predicate.equals(((FindRecordCommand) other).predicate)); // state check
    }
}
