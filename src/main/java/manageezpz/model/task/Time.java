package manageezpz.model.task;

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
        this.time = time;
    }

    public static boolean isValidTime(String time) {
        return time.matches(VALIDATION_REGEX) && checkTimeRange(time);
    }

    /**
     * Validates the range of time provided.
     * @param time String representation of time.
     * @return true if time is within the range specified, false otherwise.
     */
    public static boolean checkTimeRange(String time) {
        int timeInInt = Integer.parseInt(time);
        return (timeInInt >= 0 && timeInInt <= 2359);
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
