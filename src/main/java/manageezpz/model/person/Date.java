package manageezpz.model.person;

import java.time.LocalDate;

public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the following formats: \"yyyy-MM-dd\", " +
            "\"yyyy/MM/dd\", \"yyyy MMM dd\", \"dd MMM yyyy\", \"dd-MM-yyyy\", \"dd/MM/yyyy\"";

    public final LocalDate value;

    public Date(LocalDate date) {
        value = date;
    }
}
