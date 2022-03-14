package manageezpz.model.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time should strictly be in the format of HHMM."
            + " It should only contain numbers, 4 digits long and should not be blank";

    public static final String VALIDATION_REGEX = "\\d{4}";

    private LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(LocalTime time) {
        this.time = time;
    }

    public static boolean isValidTime(String time) {
        return time.matches(VALIDATION_REGEX);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String format(DateTimeFormatter dtf) {
        return time.format(dtf);
    }
}
