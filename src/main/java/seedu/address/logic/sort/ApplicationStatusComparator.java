package seedu.address.logic.sort;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.application.Application;
import seedu.address.model.tag.ApplicationStatusTagType;
import seedu.address.model.tag.Tag;

public class ApplicationStatusComparator implements Comparator<Application> {

    public static final String COMMAND_WORD = "status";

    @Override
    public int compare(Application o1, Application o2) {
        int compareResult = getStatusRanking(o1.getApplicationStatusTag())
                - getStatusRanking(o2.getApplicationStatusTag());
        NameComparator nameComparator = new NameComparator();
        return compareResult == 0
                ? nameComparator.compare(o1, o2)
                : -compareResult;
    }

    /**
     * Compares the two application's status tag field. Returns a negative integer, zero, or a positive integer as
     * the first argument is less than, equal to, or greater than the second base on alphanumeric order.
     * The default order from ascending to descending is as follows ACCEPTED, REJECTED, INTERVIEWED, APPLIED,
     * NOT_APPLIED, (Empty Tag).
     * An empty tag will be considered as the lowest ranking, ranked below NOT_APPLIED.
     * */
    private int getStatusRanking(Optional<Tag> t) {
        if (t.isEmpty()) {
            return 0;
        } else {
            ApplicationStatusTagType applicationStatusTagType = ApplicationStatusTagType.valueOf(t.get().toString());
            return applicationStatusTagType.getStatusRanking();
        }
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
