package seedu.address.model.schedule;

import java.time.LocalDate;
import java.util.function.Predicate;


public class ScheduleOnThisDatePredicate implements Predicate<Schedule> {
    private final LocalDate date;

    public ScheduleOnThisDatePredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Schedule schedule) {
        LocalDate scheduleDate = schedule.getScheduleDateTime().getScheduleDateTime().toLocalDate();
        return scheduleDate.isEqual(date); // !scheduleDate.isBefore(date) && !scheduleDate.isAfter(date);

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ScheduleOnThisDatePredicate)
                && date.isEqual(((ScheduleOnThisDatePredicate) other).date);
    }

}
