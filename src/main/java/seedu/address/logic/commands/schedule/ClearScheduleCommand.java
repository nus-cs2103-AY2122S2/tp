package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;

/**
 * Clears the interview schedule.
 */
public class ClearScheduleCommand extends ScheduleCommand {

    public static final String COMMAND_WORD = ScheduleCommand.COMMAND_WORD + " clear";
    public static final String MESSAGE_SUCCESS = "Interview schedule has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInterviewSchedule(new InterviewSchedule());
        model.resetAllScheduledStatus();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
