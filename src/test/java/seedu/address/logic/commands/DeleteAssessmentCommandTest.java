package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssessments.LAB_1;
import static seedu.address.testutil.TypicalAssessments.LAB_2;
import static seedu.address.testutil.TypicalAssessments.OP1;
import static seedu.address.testutil.TypicalAssessments.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAssessmentCommand}.
 */
public class DeleteAssessmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_assessmentNameInModel_deletesAssessment() {
        AssessmentName nameOfAssessmentToDelete = LAB_1.getAssessmentName();
        Assessment assessmentToDelete = LAB_1;
        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(nameOfAssessmentToDelete);

        String expectedMessage = String.format(DeleteAssessmentCommand.MESSAGE_SUCCESS, assessmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeAssessmentWithName(nameOfAssessmentToDelete);

        assertCommandSuccess(deleteAssessmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assessmentNameNotInModel_throwsCommandException() {
        AssessmentName assessmentNameNotInModel = OP1.getAssessmentName();

        DeleteAssessmentCommand deleteAssessmentCommand = new DeleteAssessmentCommand(assessmentNameNotInModel);

        assertCommandFailure(deleteAssessmentCommand, model, Messages.MESSAGE_ASSESSMENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        DeleteAssessmentCommand deleteAssessmentCommandLab1 =
                new DeleteAssessmentCommand(LAB_1.getAssessmentName());
        DeleteAssessmentCommand deleteAssessmentCommandLab2 =
                new DeleteAssessmentCommand(LAB_2.getAssessmentName());

        // same object -> returns true
        assertTrue(deleteAssessmentCommandLab1.equals(deleteAssessmentCommandLab1));

        // same values -> returns true
        DeleteAssessmentCommand deleteAssessmentCommandLab1Copy =
                new DeleteAssessmentCommand(LAB_1.getAssessmentName());
        assertTrue(deleteAssessmentCommandLab1.equals(deleteAssessmentCommandLab1Copy));

        // null -> returns false
        assertFalse(deleteAssessmentCommandLab2.equals(null));

        // different assessment name -> returns false
        assertFalse(deleteAssessmentCommandLab1.equals(deleteAssessmentCommandLab2));
    }

}
