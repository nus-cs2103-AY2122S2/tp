package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showInterviewAtIndex;
import static seedu.address.testutil.TypicalCandidates.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.getTypicalInterviewSchedule;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.schedule.DeleteScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.testutil.TypicalIndexes;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalInterviewSchedule(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Interview interviewToDelete = model.getFilteredInterviewSchedule()
                .get(TypicalIndexes.INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(TypicalIndexes.INDEX_FIRST_CANDIDATE);

        String expectedMessage =
                String.format(DeleteScheduleCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
                model.getInterviewSchedule(), new UserPrefs());

        expectedModel.deleteInterview(interviewToDelete);
        assertCommandSuccess(deleteScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewSchedule().size() + 1);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteScheduleCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInterviewAtIndex(model, TypicalIndexes.INDEX_FIRST_INTERVIEW);
        Interview interviewToDelete = model.getFilteredInterviewSchedule().get(INDEX_FIRST_INTERVIEW.getZeroBased());
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(INDEX_FIRST_INTERVIEW);

        String expectedMessage =
                String.format(DeleteScheduleCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getInterviewSchedule(), new UserPrefs());

        expectedModel.deleteInterview(interviewToDelete);
        showNoInterview(expectedModel);
        assertCommandSuccess(deleteScheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInterviewAtIndex(model, TypicalIndexes.INDEX_FIRST_INTERVIEW);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_INTERVIEW;
        // ensures that outOfBoundIndex is still in bounds of interview schedule
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInterviewSchedule().getInterviewList().size());

        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteScheduleCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptySchedule_throwsCommandException() {
        Index outOfBoundIndex = TypicalIndexes.INDEX_FIRST_INTERVIEW;
        model.updateFilteredInterviewSchedule(Model.PREDICATE_SHOW_EMPTY_INTERVIEW_SCHEDULE);
        DeleteScheduleCommand deleteScheduleCommand = new DeleteScheduleCommand(outOfBoundIndex);

        assertCommandFailure(deleteScheduleCommand, model, Messages.MESSAGE_NO_INTERVIEWS_IN_SYSTEM);
    }

    @Test
    public void equals() {
        DeleteScheduleCommand deleteScheduleFirstCommand =
                new DeleteScheduleCommand(TypicalIndexes.INDEX_FIRST_INTERVIEW);
        DeleteScheduleCommand deleteScheduleSecondCommand =
                new DeleteScheduleCommand(TypicalIndexes.INDEX_SECOND_INTERVIEW);

        // same object -> returns true
        assertTrue(deleteScheduleFirstCommand.equals(deleteScheduleFirstCommand));

        // same values -> returns true
        DeleteScheduleCommand deleteScheduleFirstCommandCopy =
                new DeleteScheduleCommand(TypicalIndexes.INDEX_FIRST_INTERVIEW);
        assertTrue(deleteScheduleFirstCommand.equals(deleteScheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteScheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteScheduleFirstCommand.equals(null));

        // different candidate -> returns false
        assertFalse(deleteScheduleFirstCommand.equals(deleteScheduleSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered interview schedule to show no interviews.
     */
    private void showNoInterview(Model model) {
        model.updateFilteredInterviewSchedule(Model.PREDICATE_SHOW_EMPTY_INTERVIEW_SCHEDULE);

        assertTrue(model.getFilteredInterviewSchedule().isEmpty());
    }
}
