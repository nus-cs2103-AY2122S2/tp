package seedu.address.model.job;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

class JobStatusComparatorTest {

    @Test
    void compare() {
        Job filledJob = new JobBuilder().withJobStatus("filled").build();
        Job vacantJob = new JobBuilder().withJobStatus("vacant").build();
        JobStatusComparator jobStatusComparator = new JobStatusComparator();

        assertTrue(jobStatusComparator.compare(filledJob, vacantJob) > 0);
        assertTrue(jobStatusComparator.compare(vacantJob, filledJob) < 0);
        assertTrue(jobStatusComparator.compare(vacantJob, vacantJob) == 0);
        assertTrue(jobStatusComparator.compare(filledJob, filledJob) == 0);
    }
}
