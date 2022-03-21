package manageezpz.model.task;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the following format : yyyy-MM-dd"
            + " Month should be between 1 and 12 and Day should be between 1 and 31";

    public static final String VALIDATION_REGEX = "\\d{4}\\D\\d{2}\\D\\d{2}";

    private String date;

    /**
     * Constructs a {@code Date}.
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public static boolean isValidDate(String date) {
        return date.matches(VALIDATION_REGEX) && validCheckDate(date);
    }

    public LocalDate getParsedDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, dtf);
        return parsedDate;
    }

    /**
     * Gets today's date.
     * @return Today's date
     */
    public static Date getTodayDate() {
        LocalDate todayDate = LocalDate.now();
        return new Date(todayDate.toString());
    }

    /**
     * Validates the format of date provided.
     * @param date String representation of date.
     * @return true if date is in the correct parsable format, false otherwise.
     * */
    public static boolean validCheckDate(String date) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        try {
            LocalDate testDate = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String format(DateTimeFormatter dtf) {
        return getParsedDate().format(dtf);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            return date.equals(((Date) obj).date);
        } else {
            return false;
        }
    }
}
