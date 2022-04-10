package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.DescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all appointment in appointment book whose description contains any of the argument keywords.
 * Keyword matching is case in-sensitive.
 */
public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -a : Finds all appointment whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " -a UTown";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindAppointmentCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        String message =
                String.format(Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW, model.getFilteredAppointmentList().size());
        return new CommandResult(message, false, false, true,
                false, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}
