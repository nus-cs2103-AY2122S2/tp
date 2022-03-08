package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose status contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindStatusCommand extends Command {

    public static final String COMMAND_WORD = "findstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose status contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [STATUS]...\n"
            + "Example: " + COMMAND_WORD + " positive\n"
            + "Example: " + COMMAND_WORD + " negative\n"
            + "Example: " + COMMAND_WORD + " close-contact\n";

    public static final String ERRMSG_STATUS = "Parameters: KEYWORD [STATUS]...\n"
            + "[STATUS] Should only be positive, negative or close-contact\n"
            + "Example: " + COMMAND_WORD + " positive\n"
            + "Example: " + COMMAND_WORD + " negative\n"
            + "Example: " + COMMAND_WORD + " close-contact";

    private final StatusContainsKeywordsPredicate predicate;

    public FindStatusCommand(StatusContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStatusCommand // instanceof handles nulls
                && predicate.equals(((FindStatusCommand) other).predicate)); // state check
    }
}
