package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_APPLICANT_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.applicant.SortApplicant.MESSAGE_SORT_APPLICANT_SUCCESS;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;

public class SortApplicantTest {
    public static final Comparator<Applicant> COMPARE_BY_DATEAPPLIED =
            Comparator.comparing(Applicant::getDateApplied);

    public static final Comparator<Applicant> COMPARE_BY_INTERVIEWDATE =
            Comparator.comparing(Applicant::getInterviewDate);

    public static final Comparator<Applicant> COMPARE_BY_JOBID =
            Comparator.comparing(Applicant::getJobId);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_sortByDateApplied_success() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "date applied");
        SortApplicant command = new SortApplicant("dateapplied");
        expectedModel.sortApplicant(COMPARE_BY_DATEAPPLIED);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortByInterview_success() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "interview date");
        SortApplicant command = new SortApplicant("interview");
        expectedModel.sortApplicant(COMPARE_BY_INTERVIEWDATE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortByJobId_success() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "job applied");
        SortApplicant command = new SortApplicant("job");
        expectedModel.sortApplicant(COMPARE_BY_JOBID);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortByDateAppliedUpperCase_success() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "date applied");
        SortApplicant command = new SortApplicant("DATEAPPLIED");
        expectedModel.sortApplicant(COMPARE_BY_DATEAPPLIED);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortByInterviewUpperCase_failure() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "interview date");
        SortApplicant command = new SortApplicant("INTERVIEW");
        expectedModel.sortApplicant(COMPARE_BY_INTERVIEWDATE);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_sortByJobIdUpperCase_success() {
        String expectedMessage = String.format(MESSAGE_SORT_APPLICANT_SUCCESS, "job applied");
        SortApplicant command = new SortApplicant("JOB");
        expectedModel.sortApplicant(COMPARE_BY_JOBID);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_emptyList_throwsCommandException() {
        model.setAddressBook(new AddressBook());
        assertTrue(model.getFilteredJobList().isEmpty());
        String expectedMessage = String.format(MESSAGE_EMPTY_APPLICANT_LIST);
        SortApplicant command = new SortApplicant("dateapplied");
        assertCommandFailure(command, model, expectedMessage);

    }

    @Test
    void equals() {
        SortApplicant sortByDateApplied = new SortApplicant("dateapplied");
        SortApplicant sortByInterview = new SortApplicant("interview");
        SortApplicant sortByJobId = new SortApplicant("job");

        assertTrue(sortByDateApplied.equals(new SortApplicant("DATEAPPLIED")));

        assertTrue(sortByInterview.equals(new SortApplicant("INTERVIEW")));

        assertTrue(sortByJobId.equals(new SortApplicant("JOB")));

        assertFalse(sortByDateApplied.equals(sortByInterview));

        assertFalse(sortByDateApplied.equals(sortByJobId));

        assertFalse(sortByInterview.equals(sortByJobId));
    }
}
