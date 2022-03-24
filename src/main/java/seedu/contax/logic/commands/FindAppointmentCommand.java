package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.commons.core.Messages;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.ContainsKeywordsPredicate;

/**
 * Finds and lists all appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "findappointment";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: Finds all appointments with names or person names that "
            + "contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: *KEYWORDS [MORE_KEYWORDS]... [BY/PERSON]*\n"
            + "`Example: " + COMMAND_WORD + "Contract Alice Meeting`";

    private final ContainsKeywordsPredicate predicate;

    public FindAppointmentCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW,
                model.getFilteredAppointmentList().size()), GuiListContentType.APPOINTMENT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof FindAppointmentCommand)) {
            return false;
        }

        return ((FindAppointmentCommand) o).predicate.equals(predicate);
    }


}
