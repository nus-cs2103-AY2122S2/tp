package seedu.linkedout.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.linkedout.commons.core.Messages;
import seedu.linkedout.model.Model;
import seedu.linkedout.model.applicant.NameContainsAllKeywordsPredicate;

/**
 * view on applicant in linkedout app whose name contains the exact argument keywords.
 * Keyword matching is case insensitive.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View an overview of a specific applicant"
            + ", specified by applicant's name (case-insensitive) and displays the applicant's information.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Steve Jobs";

    private final NameContainsAllKeywordsPredicate predicate;

    public ViewCommand(NameContainsAllKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getDefaultApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
