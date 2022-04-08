package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.predicate.CompanyContainsKeywordsPredicate;

/**
 * Keyword matching is case insensitive
 * Find company command uses User input to specify what attributes (name and/or tags) the displayed companies
 * should have.
 * The relationship between attributes is "AND" while the relationship between keywords of the same attribute
 * is "OR".
 * For example, "finde n/ shopsg dbsss" will return shopsg and dbsss companies. Furthermore, "finde t/ applied test"
 * will return all companies that has tags "applied" or "test".
 */
public class FindCompanyCommand extends Command {

    public static final String COMMAND_WORD = "findc";
    public static final String MESSAGE_NOT_QUERIED = "At least one field to find must be provided.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds companies with the same details as the "
            + "given parameters.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SEARCH_TYPE + "SEARCH_TYPE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "sgshop dbsss ";

    private final CompanyContainsKeywordsPredicate predicate;

    /**
     * Constructs FindCompanyCommand object
     * @param predicate A predicate containing all Company's name and tags queried by user
     */
    public FindCompanyCommand(CompanyContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.showCompanyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW, model.getFilteredCompanyList().size()),
                false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCompanyCommand // instanceof handles nulls
                && predicate.equals(((FindCompanyCommand) other).predicate)); // state check
    }
}
