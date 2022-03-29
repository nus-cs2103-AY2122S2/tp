package seedu.address.model.schedule;

import java.util.Comparator;
import java.time.LocalDateTime;

/**
 * A comparator to compare between objects of {@code Person}.
 *
 */
public class ScheduleComparator implements Comparator<Schedule> {
    @Override
    public int compare(Schedule s1, Schedule s2) {
        LocalDateTime d1 = s1.getScheduleDateTime().getScheduleDateTime();
        LocalDateTime d2 = s2.getScheduleDateTime().getScheduleDateTime();
        if (d2.isBefore(d1)) {
            return 1;
        }
        return -1;
    }
}