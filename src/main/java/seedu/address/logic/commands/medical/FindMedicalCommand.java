package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.model.Model;
import seedu.address.model.medical.MedicalContainsKeywordsPredicate;

/**
 * Finds and lists all medical records in MedBook whose information contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindMedicalCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final CommandType COMMAND_TYPE = CommandType.MEDICAL;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all medical records in MedBook whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " diabetes";

    private final MedicalContainsKeywordsPredicate predicate;

    public FindMedicalCommand(MedicalContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMedicalList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_MEDICALS_LISTED_NO_NRIC,
                model.getFilteredMedicalList().size()), COMMAND_TYPE);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMedicalCommand // instanceof handles nulls
                && predicate.equals(((FindMedicalCommand) other).predicate)); // state check
    }
}
