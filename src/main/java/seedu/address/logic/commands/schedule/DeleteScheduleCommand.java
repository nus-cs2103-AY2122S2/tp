package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Schedules a candidate identified using it's displayed index from the address book for an interview
 * on a specified time slot.
 */
public class DeleteScheduleCommand extends Command {

    public static final String COMMAND_WORD = ScheduleCommand.COMMAND_WORD + " delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the interview identified by the index number used in the interview schedule"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INTERVIEW_SUCCESS =
            "Successfully scheduled %1$s %2$s for interview on %3$s %4$s";

    private final Index targetIndex;
    private final LocalDateTime interviewDateTime;

    /**
     * Creates a AddScheduleCommand to schedule the candidate at specified index for an
     * interview on {@code LocalDateTime}
     */
    public DeleteScheduleCommand(Index targetIndex, LocalDateTime interviewDateTime) {
        this.targetIndex = targetIndex;
        this.interviewDateTime = interviewDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();
        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_CANDIDATES_IN_SYSTEM));
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CANDIDATE_DISPLAYED_INDEX);
        }

        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SCHEDULED_CANDIDATE_SUCCESS, toAdd.getCandidate().getName(),
                toAdd.getCandidate().getStudentId(), toAdd.getInterviewDate(), toAdd.getInterviewStartTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteScheduleCommand) other).targetIndex)
                && interviewDateTime.equals(((DeleteScheduleCommand) other).interviewDateTime)); // state check
    }
}
