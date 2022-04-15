package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.predicate.PersonContainsKeywordsPredicate;


/**
 * Finds and lists all persons in address book whose name, companyName, and tags contains any of the argument keywords.
 * Keyword matching is case insensitive
 * Find person command uses User input to specify what attributes the displayed person should have.
 * The relationship between attributes is "AND" while the relationship between keywords of the same attribute
 * is "OR".
 * For example, "findp n/ alice bob" will return alice and bob (any person whose name is "alice" or "bob").
 * In addition, "findp c/ shopsg t/ hr will return any person whose companyName is "shopsg" and tags contains "hr".
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "findp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds persons with the same details as the "
            + "given parameters.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_COMPANY + "COMPANY_NAME] "
            + "[" + PREFIX_SEARCH_TYPE + "SEARCH_TYPE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "alex david ";

    private final PersonContainsKeywordsPredicate predicate;

    /**
     * Constructs FindPersonCommand object
     * @param predicate A predicate containing all Event's attributes queried by user
     */
    public FindPersonCommand(PersonContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.showPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
