package manageezpz.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the following format : yyyy-MM-dd";

    public static final String VALIDATION_REGEX = "\\d{4}\\D\\d{2}\\D\\d{2}";

    private String date;

    public Date(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public static boolean isValidDate(String date) {
        return date.matches(VALIDATION_REGEX);
    }

    public LocalDate getParsedDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, dtf);
        return parsedDate;
    }

    public String format(DateTimeFormatter dtf) {
        return getParsedDate().format(dtf);
    }
}
