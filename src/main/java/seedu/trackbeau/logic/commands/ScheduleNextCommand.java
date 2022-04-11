package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.ui.Panel;

/**
 * View schedule of next week from last selected date.
 */
public class ScheduleNextCommand extends Command {

    public static final String COMMAND_WORD = "scheduleNext";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List bookings of next week from last selected date."
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Displaying next week schedule.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSelectedDate(model.getSelectedDate().plusDays(7));
        return new CommandResult(String.format(MESSAGE_SUCCESS), Panel.SCHEDULE_PANEL);
    }
}
