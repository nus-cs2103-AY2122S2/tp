package manageezpz.model.task;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time should strictly be in the format of HHMM."
            + " It should only contain numbers, 4 digits long and should not be blank. "
            + "Time should also be in the range of 0000 and 2359";

    public static final String VALIDATION_REGEX = "\\d{4}";

    private String time;

    /**
     * Constructs a {@code Time}.
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = time;
    }

    /**
     * Checks if a given string is a valid time.
     * @return true if a given string is a valid time, false otherwise
     */
    public static boolean isValidTime(String time) {
        return time.matches(VALIDATION_REGEX) && time.matches("([01]?[0-9]|2[0-3])[0-5][0-9]");
    }

    public String getTime() {
        return time;
    }

    public LocalTime getParsedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalTime parsedTime = LocalTime.parse(time, dtf);
        return parsedTime;
    }

    public String format(DateTimeFormatter dtf) {
        return getParsedTime().format(dtf);
    }
}
