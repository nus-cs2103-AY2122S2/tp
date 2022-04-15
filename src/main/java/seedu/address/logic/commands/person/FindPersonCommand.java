package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.name.PersonNameContainsKeywordsPredicate;
import seedu.address.model.tag.PersonTagContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names or tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Alex " + PREFIX_NAME + "Charlotte "
            + PREFIX_TAG + "friends";

    private final PersonNameContainsKeywordsPredicate namePredicate;
    private final PersonTagContainsKeywordsPredicate tagPredicate;

    /**
     * Initialises new object using {@code namePredicate} and {@code tagPredicate}
     */
    public FindPersonCommand(PersonNameContainsKeywordsPredicate namePredicate,
                             PersonTagContainsKeywordsPredicate tagPredicate) {
        this.namePredicate = namePredicate;
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(namePredicate.or(tagPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && namePredicate.equals(((FindPersonCommand) other).namePredicate)
                && tagPredicate.equals(((FindPersonCommand) other).tagPredicate)); // state check
    }
}
