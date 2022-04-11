package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.DATA_ANALYSIS;
import static seedu.address.testutil.TypicalJobs.PROJECT_MANAGER;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.job.exception.DuplicateJobException;
import seedu.address.model.job.exception.JobNotFoundException;
import seedu.address.testutil.JobBuilder;

class UniqueJobListTest {
    private final UniqueJobList uniqueJobList = new UniqueJobList();

    @Test
    public void contains_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.contains(null));
    }

    @Test
    public void contains_jobNotInList_returnsFalse() {
        assertFalse(uniqueJobList.contains(DATA_ANALYSIS));
    }

    @Test
    public void contains_jobInList_returnsTrue() {
        uniqueJobList.add(DATA_ANALYSIS);
        assertTrue(uniqueJobList.contains(DATA_ANALYSIS));
    }

    @Test
    public void contains_jobWithSameIdentityFieldsInList_returnsTrue() {
        uniqueJobList.add(DATA_ANALYSIS);
        Job editedDataAnalysis = new JobBuilder(DATA_ANALYSIS).withJobAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(uniqueJobList.contains(editedDataAnalysis));
    }

    @Test
    public void add_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.add(null));
    }

    @Test
    public void add_duplicateJob_throwsDuplicateJobException() {
        uniqueJobList.add(DATA_ANALYSIS);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.add(DATA_ANALYSIS));
    }

    @Test
    public void setJob_nullTargetJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(null, DATA_ANALYSIS));
    }

    @Test
    public void setJob_nullEditedJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJob(DATA_ANALYSIS, null));
    }

    @Test
    public void setJob_targetJobNotInList_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.setJob(DATA_ANALYSIS, DATA_ANALYSIS));
    }

    @Test
    public void setJob_editedJobIsSameJob_success() {
        uniqueJobList.add(DATA_ANALYSIS);
        uniqueJobList.setJob(DATA_ANALYSIS, DATA_ANALYSIS);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(DATA_ANALYSIS);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasSameIdentity_success() {
        uniqueJobList.add(DATA_ANALYSIS);
        Job editedDataAnalysis = new JobBuilder(DATA_ANALYSIS).withJobAddress(VALID_ADDRESS_BOB)
                .build();
        uniqueJobList.setJob(DATA_ANALYSIS, editedDataAnalysis);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(editedDataAnalysis);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasDifferentIdentity_success() {
        uniqueJobList.add(DATA_ANALYSIS);
        uniqueJobList.setJob(DATA_ANALYSIS, PROJECT_MANAGER);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(PROJECT_MANAGER);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJob_editedJobHasNonUniqueIdentity_throwsDuplicateJobException() {
        uniqueJobList.add(DATA_ANALYSIS);
        uniqueJobList.add(PROJECT_MANAGER);
        assertThrows(DuplicateJobException.class, () -> uniqueJobList.setJob(DATA_ANALYSIS, PROJECT_MANAGER));
    }

    @Test
    public void remove_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.remove(null));
    }

    @Test
    public void remove_jobDoesNotExist_throwsJobNotFoundException() {
        assertThrows(JobNotFoundException.class, () -> uniqueJobList.remove(DATA_ANALYSIS));
    }

    @Test
    public void remove_existingJob_removesJob() {
        uniqueJobList.add(DATA_ANALYSIS);
        uniqueJobList.remove(DATA_ANALYSIS);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullUniqueJobList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((UniqueJobList) null));
    }

    @Test
    public void setJobs_uniqueJobList_replacesOwnListWithProvidedUniqueJobList() {
        uniqueJobList.add(DATA_ANALYSIS);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(PROJECT_MANAGER);
        uniqueJobList.setJobs(expectedUniqueJobList);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueJobList.setJobs((List<Job>) null));
    }

    @Test
    public void setJobs_list_replacesOwnListWithProvidedList() {
        uniqueJobList.add(DATA_ANALYSIS);
        List<Job> jobList = Collections.singletonList(PROJECT_MANAGER);
        uniqueJobList.setJobs(jobList);
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(PROJECT_MANAGER);
        assertEquals(expectedUniqueJobList, uniqueJobList);
    }

    @Test
    public void setJobs_listWithDuplicateJobs_throwsDuplicateJobException() {
        List<Job> listWithDuplicateJobs = Arrays.asList(DATA_ANALYSIS, DATA_ANALYSIS);
        assertThrows(DuplicateJobException.class, () ->
                uniqueJobList.setJobs(listWithDuplicateJobs));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueJobList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(uniqueJobList.hashCode(), new UniqueJobList().hashCode());

        // add an Applicant into second Unique Applicant List  -> returns different hashcode
        UniqueJobList expectedUniqueJobList = new UniqueJobList();
        expectedUniqueJobList.add(DATA_ANALYSIS);
        assertNotEquals(uniqueJobList.hashCode(), expectedUniqueJobList.hashCode());

        // add the same Applicant into first Unique Applicant List, both have the same Applicant inside list
        // -> return same hashcode
        uniqueJobList.add(DATA_ANALYSIS);
        assertEquals(uniqueJobList.hashCode(), expectedUniqueJobList.hashCode());
    }
}
