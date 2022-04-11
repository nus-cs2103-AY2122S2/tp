//@author e0543517
package seedu.address.model.job;

import java.util.Comparator;

public class JobStatusComparator implements Comparator<Job> {
    @Override
    public int compare(Job firstJob, Job secondJob) {
        return -firstJob.getJobStatus().jobStatus.compareTo(secondJob.getJobStatus().jobStatus);
    }
}
