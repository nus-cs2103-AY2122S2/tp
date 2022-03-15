package manageezpz.model.task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the following format : yyyy-MM-dd"
            + " Month should be between 1 and 12 and Day should be between 1 and 31";

    public static final String VALIDATION_REGEX = "\\d{4}\\D\\d{2}\\D\\d{2}";

    private String date;

    public Date(String date) {
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
}
