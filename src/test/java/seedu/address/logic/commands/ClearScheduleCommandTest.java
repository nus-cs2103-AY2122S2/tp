package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.candidate.InterviewStatus.NOT_SCHEDULED;
import static seedu.address.model.candidate.InterviewStatus.SCHEDULED;
import static seedu.address.testutil.TypicalCandidates.getTypicalAddressBook;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.ClearScheduleCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.candidate.Candidate;
import seedu.address.testutil.CandidateBuilder;

public class ClearScheduleCommandTest {

    @Test
    public void execute_emptyInterviewSchedule_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearScheduleCommand(), model, ClearScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInterviewSchedule_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());
        expectedModel.setInterviewSchedule(new InterviewSchedule());

        assertCommandSuccess(new ClearScheduleCommand(), model, ClearScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allCandidatesStatusReset_success() {
        Candidate candidateScheduled = new CandidateBuilder().withInterviewStatus(SCHEDULED).build();
        Candidate candidateNotScheduled = new CandidateBuilder().withInterviewStatus(NOT_SCHEDULED).build();
        AddressBook addressBook = new AddressBook();
        InterviewSchedule interviewSchedule = new InterviewSchedule();
        Model model = new ModelManager(addressBook, interviewSchedule, new UserPrefs());
        model.addCandidate(candidateScheduled);
        Model expectedModel = new ModelManager(addressBook, interviewSchedule, new UserPrefs());
        expectedModel.setInterviewSchedule(new InterviewSchedule());
        expectedModel.addCandidate(candidateNotScheduled);

        assertCommandSuccess(new ClearScheduleCommand(), model, ClearScheduleCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
