package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.NameApplicantContainsKeywordsPredicate;

/**
 * Finds and lists all applicants in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindApplicant extends Command {

    public static final String COMMAND_WORD = "findapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all applicants whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameApplicantContainsKeywordsPredicate predicate;

    public FindApplicant(NameApplicantContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()),
                true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindApplicant // instanceof handles nulls
                && predicate.equals(((FindApplicant) other).predicate)); // state check
    }

}
