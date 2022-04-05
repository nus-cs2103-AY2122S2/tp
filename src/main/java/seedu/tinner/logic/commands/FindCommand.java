package seedu.tinner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_ROLE_NAME;

import seedu.tinner.model.Model;
import seedu.tinner.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.tinner.model.role.RoleNameContainsKeywordsPredicate;

/**
 * Finds and lists all companies or roles whose name contains any of the argument keywords.
 * Search type of search conducted depends on the format of user input.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "\n"
            + "Function: Finds all companies whose names contain any of the specified keywords (case-insensitive) and"
            + " roles contains any of the specified keywords (case-insensitive). Displays them as a list with index "
            + "numbers."
            + "\n"
            + "Format: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "COMPANY_KEYWORD [MORE_COMPANY_KEYWORDS] "
            + PREFIX_ROLE_NAME + "ROLE_KEYWORD [MORE_ROLE_KEYWORDS] "
            + "\n"
            + "Example: " + COMMAND_WORD + " c/amazon r/engineer";

    public static final String MESSAGE_FIND_SUCCESS = "%1$d companies listed!\n"
            + "Company keyword(s) entered: %2$s\n"
            + "Role keyword(s) entered: %3$s";

    private final CompanyNameContainsKeywordsPredicate companyPredicate;
    private final RoleNameContainsKeywordsPredicate rolePredicate;

    /**
     * @param companyPredicate Predicate provided to filter through companies
     * @param rolePredicate    Predicate provided to filter through roles
     */
    public FindCommand(CompanyNameContainsKeywordsPredicate companyPredicate,
                       RoleNameContainsKeywordsPredicate rolePredicate) {
        this.companyPredicate = companyPredicate;
        this.rolePredicate = rolePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(companyPredicate, rolePredicate);
        return new CommandResult(
                String.format(MESSAGE_FIND_SUCCESS, model.getFilteredCompanyList().size(), companyPredicate,
                        rolePredicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && companyPredicate.equals(((FindCommand) other).companyPredicate)
                && rolePredicate.equals(((FindCommand) other).rolePredicate)); // state check
    }
}
