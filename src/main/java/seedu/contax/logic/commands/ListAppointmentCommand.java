package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.contax.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;

/**
 * Lists all appointments in the schedule to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "listappointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.clearDisplayedAppointmentSlots();
        return new CommandResult(MESSAGE_SUCCESS, GuiListContentType.APPOINTMENT);
    }
}
