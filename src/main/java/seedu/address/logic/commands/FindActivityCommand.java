package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.activity.ActivityContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose Activities contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindActivityCommand extends Command {

    public static final String COMMAND_WORD = "findactivity";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Command: Finds all persons whose activities contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [ACTIVITY]...\n\n"
            + "Example: " + COMMAND_WORD + " badminton\n"
            + "Example: " + COMMAND_WORD + " basketball soccer golf\n";

    public static final String ERRMSG_STATUS = "Parameters: KEYWORD [ACTIVITY]\n"
            + "The specified keywords (case-insensitive) should only contain alphabets\n"
            + "Parameters: KEYWORD [ACTIVITY]...\n\n"
            + "Example: " + COMMAND_WORD + " badminton\n"
            + "Example: " + COMMAND_WORD + " basketball soccer golf\n";

    private final ActivityContainsKeywordsPredicate predicate;

    public FindActivityCommand(ActivityContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindActivityCommand // instanceof handles nulls
                && predicate.equals(((FindActivityCommand) other).predicate)); // state check
    }
}
