package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * View schedule for the week of selected date.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List bookings for the week containing date."
            + " Parameters: "
            + PREFIX_DATE + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10-10-2022";

    public static final String MESSAGE_SUCCESS = "Displaying schedule for the week of %1$s";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDate selectedDate;

    /**
     * Create a new ScheduleCommand to display schedule for week of {@code selectDate}
     */
    public ScheduleCommand(LocalDate selectedDate) {
        requireNonNull(selectedDate);
        this.selectedDate = selectedDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSelectedDate(selectedDate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, selectedDate.format(formatter)),
            false, false, false, false, false,
            false, false, false, Panel.SCHEDULE_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ScheduleCommand // instanceof handles nulls
            && selectedDate.equals(((ScheduleCommand) other).selectedDate));
    }
}
