package seedu.address.logic.sort;

import java.time.LocalDateTime;
import java.util.Comparator;

import seedu.address.model.application.Application;

public class InterviewSlotComparator implements Comparator<Application> {

    public static final String COMMAND_WORD = "interview";

    /**
     * Compares the two application's interview slot field. Returns a negative integer, zero, or a positive integer as
     * the first argument is less than, equal to, or greater than the second base on alphanumeric order.
     * */
    @Override
    public int compare(Application o1, Application o2) {
        LocalDateTime app1 = o1.getInterviewSlot().getValue();
        LocalDateTime app2 = o2.getInterviewSlot().getValue();
        NameComparator nameComparator = new NameComparator();
        if (app1.isAfter(app2)) {
            return 1;
        } else if (app1.isBefore(app2)) {
            return -1;
        } else {
            return nameComparator.compare(o1, o2);
        }
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
