package seedu.address.logic.commands.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

/**
 * Schedules a candidate identified using it's displayed index from the address book for an interview
 * on a specified time slot.
 */
public class EditScheduleCommand extends ScheduleCommand {

    public static final String COMMAND_WORD = ScheduleCommand.COMMAND_WORD + " edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the interview identified by the index number in the interview schedule.\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS =
            "Successfully edited interview for %1$s";

    private final Index index;
    private final LocalDateTime newDateTime;

    /**
     * Creates a AddScheduleCommand to schedule the candidate at specified index for an
     * interview on {@code LocalDateTime}
     */
    public EditScheduleCommand(Index index, LocalDateTime newDateTime) {
        requireNonNull(index);
        requireNonNull(newDateTime);
        this.index = index;
        this.newDateTime = newDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewSchedule();
        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_INTERVIEWS_IN_SYSTEM));
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToEdit = lastShownList.get(index.getZeroBased());
        Interview editedInterview = createEditedInterview(interviewToEdit, newDateTime);

        if (model.hasConflictingInterview(editedInterview)) {
            throw new CommandException(MESSAGE_CONFLICTING_INTERVIEW);
        }
        model.setInterview(interviewToEdit, editedInterview);
        model.updateFilteredInterviewSchedule(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS, interviewToEdit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditScheduleCommand // instanceof handles nulls
                && index.equals(((EditScheduleCommand) other).index)); // state check
    }

    /**
     * Creates and returns a {@code Candidate} with the details of {@code candidateToEdit}
     * edited with {@code editCandidateDescriptor}.
     */
    private static Interview createEditedInterview(Interview interviewToEdit, LocalDateTime newDateTime) {
        assert interviewToEdit != null;
        assert newDateTime != null;

        return new Interview(interviewToEdit.getCandidate(), newDateTime);
    }
}
