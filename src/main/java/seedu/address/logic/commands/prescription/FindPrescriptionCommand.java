package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.Model;
import seedu.address.model.prescription.PrescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all prescriptions in MedBook belonging to the currently viewed patient whose information contains
 * any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all prescriptions belonging to the currently viewed patient whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " panadol";

    private final PrescriptionContainsKeywordsPredicate predicate;

    public FindPrescriptionCommand(PrescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPrescriptionList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW,
                model.getFilteredPrescriptionList().size(), ViewedNric.getViewedNric().toString()), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPrescriptionCommand // instanceof handles nulls
                && predicate.equals(((FindPrescriptionCommand) other).predicate)); // state check
    }
}
