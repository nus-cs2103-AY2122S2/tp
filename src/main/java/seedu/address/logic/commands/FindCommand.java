package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: FIELD KEYWORD [MORE_FIELD] [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob i/Undecided";

    public static final String MESSAGE_NO_KEYWORD = "Field must contain a keyword. \n"
            + "Parameters: FIELD KEYWORD [MORE_FIELD] [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob i/Undecided";

    private final CombineContainsKeywordsPredicate predicate;

    public FindCommand(CombineContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
