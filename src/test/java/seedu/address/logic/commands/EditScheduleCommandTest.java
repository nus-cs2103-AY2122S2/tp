package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.logic.commands.schedule.EditScheduleCommand.MESSAGE_EXPIRED_INTERVIEW;
import static seedu.address.testutil.TypicalCandidates.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CANDIDATE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.TYPICAL_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalInterviews.VALID_NO_CONFLICT_INTERVIEW_DATE_TIME;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.InterviewSchedule;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.TypicalIndexes;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class EditScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);
        Interview interviewInFilteredList =
                model.getFilteredInterviewSchedule().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        Interview editedInterview =
                new InterviewBuilder(interviewInFilteredList)
                        .withInterviewDateTime(VALID_NO_CONFLICT_INTERVIEW_DATE_TIME).build();
        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(INDEX_FIRST_INTERVIEW, VALID_NO_CONFLICT_INTERVIEW_DATE_TIME);

        String expectedMessage = String.format(EditScheduleCommand.MESSAGE_EDIT_INTERVIEW_SUCCESS,
                interviewInFilteredList + " to " + editedInterview.getInterviewDate() + " "
                        + editedInterview.getInterviewStartTime());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new InterviewSchedule(model.getInterviewSchedule()), new UserPrefs());
        expectedModel.setInterview(model.getFilteredInterviewSchedule().get(0), editedInterview);
        expectedModel.updateFilteredInterviewSchedule(Model.PREDICATE_SHOW_ALL_INTERVIEWS);

        assertCommandSuccess(editScheduleCommand, model, expectedMessage, expectedModel); //change interview time slot
    }

    @Test
    public void execute_conflictingInterviewUnfilteredList_failure() {
        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(INDEX_FIRST_CANDIDATE, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_CONFLICTING_INTERVIEW);
    }

    @Test
    public void execute_conflictingInterviewFilteredList_failure() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);

        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(INDEX_FIRST_CANDIDATE, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(editScheduleCommand, model, EditScheduleCommand.MESSAGE_CONFLICTING_INTERVIEW);
    }

    @Test
    public void execute_invalidInterviewIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewSchedule().size() + 1);
        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(outOfBoundIndex, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidInterviewIndexFilteredList_failure() {
        showInterviewAtIndex(model, INDEX_FIRST_INTERVIEW);
        Index outOfBoundIndex = INDEX_SECOND_INTERVIEW;
        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(outOfBoundIndex, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(editScheduleCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_expiredInterview_failure() {
        Interview interviewToEdit = new InterviewBuilder().withInterviewDateTime(LocalDateTime.now()
                .minusMinutes(1)).build();
        model.addInterview(interviewToEdit);
        EditScheduleCommand editScheduleCommand =
                new EditScheduleCommand(INDEX_FIRST_INTERVIEW, TYPICAL_INTERVIEW_DATE_TIME);

        assertCommandFailure(editScheduleCommand, model, MESSAGE_EXPIRED_INTERVIEW);
    }

    @Test
    public void equals() {
        EditScheduleCommand editScheduleFirstCommand =
                new EditScheduleCommand(TypicalIndexes.INDEX_FIRST_INTERVIEW, TYPICAL_INTERVIEW_DATE_TIME);
        EditScheduleCommand editScheduleSecondCommand =
                new EditScheduleCommand(TypicalIndexes.INDEX_SECOND_INTERVIEW, TYPICAL_INTERVIEW_DATE_TIME);

        // same object -> returns true
        assertTrue(editScheduleFirstCommand.equals(editScheduleFirstCommand));

        // same values -> returns true
        EditScheduleCommand editScheduleFirstCommandCopy =
                new EditScheduleCommand(TypicalIndexes.INDEX_FIRST_INTERVIEW, TYPICAL_INTERVIEW_DATE_TIME);
        assertTrue(editScheduleFirstCommand.equals(editScheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(editScheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(editScheduleFirstCommand.equals(null));

        // different candidate -> returns false
        assertFalse(editScheduleFirstCommand.equals(editScheduleSecondCommand));
    }
}
