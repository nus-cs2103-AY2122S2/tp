package seedu.address.logic.commands.patron;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.patron.NameContainsKeywordsPredicate;

/**
 * Finds and lists all patrons in LibTask whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPatronCommand extends Command {

    public static final String MESSAGE_USAGE = PATRON_COMMAND_GROUP + " " + FIND_COMMAND_WORD
            + ": Finds all patrons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + PATRON_COMMAND_GROUP + " " + FIND_COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindPatronCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatronList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATRONS_LISTED_OVERVIEW, model.getFilteredPatronList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPatronCommand // instanceof handles nulls
                && predicate.equals(((FindPatronCommand) other).predicate)); // state check
    }
}
