package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.commons.core.Messages;
import seedu.contax.model.Model;
import seedu.contax.model.appointment.HasClientPredicate;

public class FindAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "findappointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " : Finds all appointments with the specified client name. \n"
            + "Parameters: n/NAME\n"
            + "Example: " + COMMAND_WORD + " n/Johnny";

    private final HasClientPredicate predicate;

    public FindAppointmentCommand(HasClientPredicate predicate) {
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
