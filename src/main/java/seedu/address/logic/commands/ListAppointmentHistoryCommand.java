package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT_HISTORY;

import seedu.address.model.Model;

/**
 * Lists all appointment history in the appointment book to the user.
 */
public class ListAppointmentHistoryCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all appointment history";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getAppointmentHistoryBook().updateAppointmentHistoryList(model);
        model.updateFilteredAppointmentHistoryList(PREDICATE_SHOW_ALL_APPOINTMENT_HISTORY);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, false, true, false, false, false);

    }
}
