package seedu.address.model.schedule;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A comparator to compare between objects of {@code Person}.
 *
 */
public class ScheduleComparator implements Comparator<Schedule> {
    @Override
    public int compare(Schedule s1, Schedule s2) {
        LocalDateTime d1 = s1.getScheduleDateTime().getScheduleDateTime();
        LocalDateTime d2 = s2.getScheduleDateTime().getScheduleDateTime();
        return d2.isBefore(d1) ? 1 : (d2.isAfter(d1) ? -1 : 0);
    }
}
