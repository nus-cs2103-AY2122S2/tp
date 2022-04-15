package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.Model;
import seedu.address.model.consultation.ConsultationContainsKeywordsPredicate;

/**
 * Finds and lists all consultations in MedBook belonging to the current patient viewed whose information contains any
 * of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindConsultationCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all consultations belonging to the currently viewed patient whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " infection";

    private final ConsultationContainsKeywordsPredicate predicate;

    public FindConsultationCommand(ConsultationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredConsultationList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_CONSULTATION_LISTED_OVERVIEW,
                model.getFilteredConsultationList().size(), ViewedNric.getViewedNric().toString()), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindConsultationCommand // instanceof handles nulls
                && predicate.equals(((FindConsultationCommand) other).predicate)); // state check
    }
}
