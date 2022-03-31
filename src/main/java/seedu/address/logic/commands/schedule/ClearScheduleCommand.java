package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.candidate.Candidate;

/**
 * Clears the interview schedule.
 */
public class ClearScheduleCommand extends ScheduleCommand {

    public static final String COMMAND_WORD = ScheduleCommand.COMMAND_WORD + " clear";
    public static final String MESSAGE_SUCCESS = "Interview schedule has been cleared!";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Candidate> candidatesToSetComplete = model.getExpiredInterviewCandidates();
        for (Candidate c : candidatesToSetComplete) {
            model.setCandidate(c, c.triggerInterviewStatusCompleted());
        }
        model.setInterviewSchedule(new InterviewSchedule());
        model.resetAllScheduledStatus();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
