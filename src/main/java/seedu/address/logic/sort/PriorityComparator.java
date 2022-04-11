package seedu.address.logic.sort;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.application.Application;
import seedu.address.model.tag.PriorityTagType;
import seedu.address.model.tag.Tag;

public class PriorityComparator implements Comparator<Application> {

    public static final String COMMAND_WORD = "priority";

    /**
     * Compares the two application's priority field. Returns a negative integer, zero, or a positive integer as
     * the first argument is less than, equal to, or greater than the second base on alphanumeric order.
     * The default order from ascending to descending is as follows (Empty Tag), LOW, MEDIUM, HIGH.
     * An empty tag will be considered as the lowest ranking, ranked below LOW.
     * */
    @Override
    public int compare(Application o1, Application o2) {
        int compareResult = getPriorityRanking(o1.getPriorityTag()) - getPriorityRanking(o2.getPriorityTag());
        NameComparator nameComparator = new NameComparator();
        return compareResult == 0
                ? nameComparator.compare(o1, o2)
                : compareResult;
    }

    private int getPriorityRanking(Optional<Tag> t) {
        if (t.isEmpty()) {
            return 0;
        } else {
            PriorityTagType priorityTagType = PriorityTagType.valueOf(t.get().toString());
            return priorityTagType.getPriorityRanking();
        }
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
