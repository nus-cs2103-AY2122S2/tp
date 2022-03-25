package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_CANDIDATE_NOT_AVAILABLE;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_CONFLICTING_INTERVIEW;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_DUPLICATE_CANDIDATE_INTERVIEW;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_NOT_OFFICE_HOUR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalInterviews.AFTER_OFFICE_HOURS;
import static seedu.address.testutil.TypicalInterviews.BEFORE_OFFICE_HOURS;
import static seedu.address.testutil.TypicalInterviews.INTERVIEW_AMY_TYPICAL;
import static seedu.address.testutil.TypicalInterviews.ON_WEEKEND;
import static seedu.address.testutil.TypicalInterviews.THURSDAY_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalInterviews.TUESDAY_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalInterviews.TYPICAL_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;


public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new InterviewSchedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Candidate candidateToInterview = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime interviewDateTime = TUESDAY_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);
        Interview toAdd = new Interview(candidateToInterview, interviewDateTime);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULED_CANDIDATE_SUCCESS,
                toAdd.getCandidate().getName(), toAdd.getCandidate().getStudentId(),
                toAdd.getInterviewDate(), toAdd.getInterviewStartTime());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getInterviewSchedule(), new UserPrefs());

        expectedModel.addInterview(toAdd);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, TUESDAY_INTERVIEW_DATE_TIME);

        Candidate candidateToInterview = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime interviewDateTime = TUESDAY_INTERVIEW_DATE_TIME;

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_SCHEDULED_CANDIDATE_SUCCESS,
                candidateToInterview.getName(), candidateToInterview.getStudentId(),
                interviewDateTime.toLocalDate(), interviewDateTime.toLocalTime());

        Interview toAdd = new Interview(candidateToInterview, interviewDateTime);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                new InterviewSchedule(), new UserPrefs());

        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.addInterview(toAdd);
        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_SECOND_PERSON, TYPICAL_INTERVIEW_DATE_TIME);
        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyList_throwsCommandException() {
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_EMPTY_LIST);
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_NO_CANDIDATES_IN_SYSTEM);
    }

    @Test
    public void execute_hasSameCandidate_throwsCommandException() {
        Candidate candidateToInterview = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.addInterview(new InterviewBuilder().withCandidate(candidateToInterview)
                .withInterviewDateTime(TYPICAL_INTERVIEW_DATE_TIME).build());
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(scheduleCommand, model, MESSAGE_DUPLICATE_CANDIDATE_INTERVIEW);
    }

    @Test
    public void execute_hasConflictingInterview_throwsCommandException() {
        model.addInterview(INTERVIEW_AMY_TYPICAL);
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, MESSAGE_CONFLICTING_INTERVIEW);
    }

    @Test
    public void execute_hasNoMatchingAvailability_throwsCommandException() {
        LocalDateTime interviewDateTime = THURSDAY_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, MESSAGE_CANDIDATE_NOT_AVAILABLE);
    }

    @Test
    public void execute_beforeOfficeHours_throwsCommandException() {
        LocalDateTime interviewDateTime = BEFORE_OFFICE_HOURS;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, MESSAGE_NOT_OFFICE_HOUR);
    }

    @Test
    public void execute_afterOfficeHours_throwsCommandException() {
        LocalDateTime interviewDateTime = AFTER_OFFICE_HOURS;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, MESSAGE_NOT_OFFICE_HOUR);
    }

    @Test
    public void execute_interviewDuringWeekend_throwsCommandException() {
        LocalDateTime interviewDateTime = ON_WEEKEND;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FOURTH_PERSON, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, MESSAGE_NOT_OFFICE_HOUR);
    }

    @Test
    public void equals() {
        ScheduleCommand scheduleFirstCommand = new ScheduleCommand(INDEX_FIRST_PERSON, TYPICAL_INTERVIEW_DATE_TIME);
        ScheduleCommand scheduleSecondCommand = new ScheduleCommand(INDEX_SECOND_PERSON, TYPICAL_INTERVIEW_DATE_TIME);

        // same object -> returns true
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommand));

        // same values -> returns true
        ScheduleCommand scheduleFirstCommandcopy = new ScheduleCommand(INDEX_FIRST_PERSON, TYPICAL_INTERVIEW_DATE_TIME);
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommandcopy));

        // different types -> returns false
        assertFalse(scheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleSecondCommand));
    }
}
