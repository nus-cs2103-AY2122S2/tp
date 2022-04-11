package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.testutil.TypicalApplicants;

public class MarkApplicantTest {
    private Model model = new ModelManager(TypicalApplicants.getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexValidStatus_success() {
        Applicant applicantToMark = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        MarkApplicant markApplicant = new MarkApplicant(INDEX_FIRST_APPLICANT, new ApplicantStatus("1"));

        String expectedMessage = String.format(MarkApplicant.MESSAGE_SUCCESS, applicantToMark,
                new ApplicantStatus("1"));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased()),
                applicantToMark);

        assertCommandSuccess(markApplicant, model, expectedMessage, true, false, true, expectedModel);
    }

    @Test
    void execute_invalidIndex_failure() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);

        Applicant applicantToMark = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        MarkApplicant markApplicant = new MarkApplicant(indexOutOfBounds, new ApplicantStatus("3"));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setApplicant(model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased()),
                applicantToMark);

        assertCommandFailure(markApplicant, model,
                String.format(MESSAGE_INVALID_INDEX, MarkApplicant.MESSAGE_USAGE));
    }

    @Test
    void equals_differentIndex_failure() {
        MarkApplicant markApplicant = new MarkApplicant(INDEX_FIRST_APPLICANT, new ApplicantStatus("1"));
        assertTrue(INDEX_SECOND_APPLICANT.getZeroBased() < model.getAddressBook().getApplicantList().size());
        assertNotEquals(markApplicant, new MarkApplicant(INDEX_SECOND_APPLICANT, new ApplicantStatus("1")));
    }

    @Test
    void equals_differentApplicantStatus_failure() {
        MarkApplicant markApplicant = new MarkApplicant(INDEX_FIRST_APPLICANT, new ApplicantStatus("1"));

        assertNotEquals(markApplicant, new MarkApplicant(INDEX_FIRST_APPLICANT, new ApplicantStatus("2")));
    }

}
