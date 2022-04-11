package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AttributeContainsKeywordsPredicate;


/**
 * Finds and lists all persons in address book whose attribute contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Attributes can be accessed by adding prefixes before the keywords.\n"
            + "Parameters: PREFIX/KEYWORD [MORE_KEYWORDS]...\n"
            + "Note that [MORE_KEYWORDS] only apply for syntax n/ a/ t/ "
            + "and only one prefix can be used for find. \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie "
            + "or " + COMMAND_WORD + " " + PREFIX_ACADEMIC_MAJOR + "Computer Science";

    public static final String COMMAND_CONTAINS_MULTIPLES_PREFIXES = COMMAND_WORD + " can only take in one prefix.";

    private final AttributeContainsKeywordsPredicate predicate;

    public FindCommand(AttributeContainsKeywordsPredicate predicate) {
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
