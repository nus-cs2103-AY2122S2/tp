package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.trackbeau.model.Model;

/**
 * Clears trackBeau.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List bookings for the week containing date."
            + "parameters: "
            + PREFIX_DATE + "DATE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10-10-2022";

    public static final String MESSAGE_SUCCESS = "Displaying schedule for the week of %1$s";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDate selectedDate;

    public ScheduleCommand(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSelectedDate(selectedDate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, selectedDate.format(formatter)),
            false, false, false, false, false,
            false, false, false, true);
    }
}
