package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalInterviews.TYPICAL_INTERVIEW_DATE_TIME;
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


public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new InterviewSchedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Candidate candidateToInterview = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
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
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Candidate candidateToInterview = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, interviewDateTime);

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
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_SECOND_PERSON, interviewDateTime);
        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptyList_throwsCommandException() {
        Index outOfBoundIndex = INDEX_FIRST_PERSON;
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_EMPTY_LIST);
        LocalDateTime interviewDateTime = TYPICAL_INTERVIEW_DATE_TIME;
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, interviewDateTime);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_NO_CANDIDATES_IN_SYSTEM);
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
