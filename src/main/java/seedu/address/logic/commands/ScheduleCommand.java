package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;

/**
 * Deletes a candidate identified using it's displayed index from the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Schedules the candidate identified by the index number for an interview on given date and time.\n"
            + "Parameters: INDEX (must be a positive integer) + /at + DATE (in dd-mm-yyyy format)"
            + "TIME (in hh:mm format)\n"
            + "Example: " + COMMAND_WORD + " 1 23-03-2022 13:30";

    public static final String MESSAGE_SCHEDULED_CANDIDATE_SUCCESS =
            "Successfully scheduled %1$s for interview on %2$s";

    public static final String MESSAGE_DUPLICATE_INTERVIEW =
            "Duplicate interview found in system!";

    public static final String MESSAGE_CONFLICTING_INTERVIEW =
            "Interview for another candidate found at the same timeslot!";

    private final Index targetIndex;
    private final LocalDateTime interviewDateTime;

    public ScheduleCommand(Index targetIndex, LocalDateTime interviewDateTime) {
        this.targetIndex = targetIndex;
        this.interviewDateTime = interviewDateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_CANDIDATES_IN_SYSTEM));
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person candidateToInterview = lastShownList.get(targetIndex.getZeroBased());
        Interview toAdd = new Interview(candidateToInterview, interviewDateTime);

        if (model.hasInterview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        if (model.hasConflictingInterview(toAdd)) {
            throw new CommandException(MESSAGE_CONFLICTING_INTERVIEW);
        }

        model.addInterview(toAdd);
        return new CommandResult(String.format(MESSAGE_SCHEDULED_CANDIDATE_SUCCESS, toAdd.getCandidate(),
                toAdd.getInterviewDateTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleCommand // instanceof handles nulls
                && targetIndex.equals(((ScheduleCommand) other).targetIndex)
                && interviewDateTime.equals(((ScheduleCommand) other).interviewDateTime)); // state check
    }
}
