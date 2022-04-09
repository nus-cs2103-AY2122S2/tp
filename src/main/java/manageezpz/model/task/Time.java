package manageezpz.model.task;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Time should be in the format of HHmm, "
            + "where it should only contain numbers that is 4 digits long.\n"
            + "HH should only be between 00 and 23 and mm should only be between 00 and 59.";

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
     * @param time the time to be checked.
     * @return true if a given string is a valid time, false otherwise.
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

    /**
     * Formats the given time.
     * @param dtf the DateTimeFormatter to be used to format the time.
     * @return the formatted time.
     */
    public String format(DateTimeFormatter dtf) {
        return getParsedTime().format(dtf);
    }
}
