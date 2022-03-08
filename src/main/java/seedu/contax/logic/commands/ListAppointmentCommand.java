package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.model.Model;
import seedu.contax.ui.ListContentType;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "listAppointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, ListContentType.APPOINTMENT);
    }
}
