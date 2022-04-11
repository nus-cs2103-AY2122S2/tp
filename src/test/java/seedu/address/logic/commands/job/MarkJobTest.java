package seedu.address.logic.commands.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showJobAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobStatus;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.TypicalJobs;

public class MarkJobTest {
    private Model model = new ModelManager(TypicalJobs.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Job jobToMark = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());

        MarkJob markJob;
        String expectedMessage = "";
        Job markedJob;

        if (jobToMark.getJobStatus().equals(new JobStatus("vacant"))) {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("filled"));
            markedJob = new Job(jobToMark, new JobStatus("filled"));
            expectedMessage = String.format(MarkJob.MESSAGE_SUCCESS, markedJob);
        } else {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("vacant"));
            markedJob = new Job(jobToMark, new JobStatus("vacant"));
            expectedMessage = String.format(MarkJob.MESSAGE_SUCCESS, markedJob);
        }

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased()),
                markedJob);

        assertCommandSuccess(markJob, model, expectedMessage, false, true, true, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showJobAtIndex(model, INDEX_FIRST_JOB);

        Job jobToMark = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        MarkJob markJob;
        String expectedMessage = "";
        Job markedJob;

        if (jobToMark.getJobStatus().equals(new JobStatus("vacant"))) {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("filled"));
            markedJob = new Job(jobToMark, new JobStatus("filled"));
            expectedMessage = String.format(MarkJob.MESSAGE_SUCCESS, markedJob);
        } else {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("vacant"));
            markedJob = new Job(jobToMark, new JobStatus("vacant"));
            expectedMessage = String.format(MarkJob.MESSAGE_SUCCESS, markedJob);
        }

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(0), markedJob);

        assertCommandSuccess(markJob, model, expectedMessage, false, true, true, expectedModel);
    }

    @Test
    public void execute_jobStatusUpToDate_failure() {
        Job jobToMark = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());

        MarkJob markJob;
        String expectedMessage = "";
        Job markedJob;

        if (jobToMark.getJobStatus().equals(new JobStatus("vacant"))) {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("vacant"));
            markedJob = new Job(jobToMark, new JobStatus("vacant"));
            expectedMessage = String.format(MarkJob.MESSAGE_JOBSTATUS_UP_TO_DATE,
                    new JobStatus("vacant"));
        } else {
            markJob = new MarkJob(INDEX_FIRST_JOB, new JobStatus("filled"));
            markedJob = new Job(jobToMark, new JobStatus("filled"));
            expectedMessage = String.format(MarkJob.MESSAGE_JOBSTATUS_UP_TO_DATE,
                    new JobStatus("filled"));
        }

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setJob(model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased()),
                markedJob);

        assertCommandFailure(markJob, model, expectedMessage);
    }

    @Test
    public void execute_invalidJobIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        MarkJob markCommand = new MarkJob(outOfBoundIndex, new JobStatus("filled"));

        assertCommandFailure(markCommand, model,
                String.format(MESSAGE_INVALID_INDEX, MarkJob.MESSAGE_USAGE));
    }

    @Test
    public void execute_invalidJobIndexFilteredList_failure() {
        showJobAtIndex(model, INDEX_FIRST_JOB);
        Index outOfBoundIndex = INDEX_SECOND_JOB;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getJobList().size());

        MarkJob markCommand = new MarkJob(outOfBoundIndex, new JobStatus("filled"));

        assertCommandFailure(markCommand, model,
                String.format(MESSAGE_INVALID_INDEX, MarkJob.MESSAGE_USAGE));
    }

    @Test
    public void testEquals() {
        MarkJob markJobVacant = new MarkJob(INDEX_FIRST_JOB, new JobStatus("vacant"));
        MarkJob markJobFilled = new MarkJob(INDEX_SECOND_JOB, new JobStatus("filled"));

        // compare against capitalised job status
        MarkJob vacantCapitalized = new MarkJob(INDEX_FIRST_JOB, new JobStatus("VACANT"));
        assertTrue(markJobVacant.equals(vacantCapitalized));

        MarkJob filledCapitalized = new MarkJob(INDEX_SECOND_JOB, new JobStatus("FILLED"));
        assertTrue(markJobFilled.equals(filledCapitalized));

        // compare against different command
        assertFalse(markJobFilled.equals(new AddJob(new JobBuilder().build())));

        // compare against different command
        assertFalse(markJobVacant.equals(new AddJob((new JobBuilder().build()))));

        // compare against different indexes
        assertFalse(markJobVacant.equals(new MarkJob(INDEX_SECOND_JOB, new JobStatus("vacant"))));

        assertFalse(markJobFilled.equals(new MarkJob(INDEX_FIRST_JOB, new JobStatus("filled"))));

        // compare against different index, different job status
        assertFalse(markJobFilled.equals(markJobVacant));

    }
}
