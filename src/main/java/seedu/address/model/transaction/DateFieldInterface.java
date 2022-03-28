package seedu.address.model.transaction;

import java.time.LocalDate;

public interface DateFieldInterface {
    LocalDate getDate();

    default boolean isBefore(DateFieldInterface date) {
        return getDate().isBefore(date.getDate()) ||
                getDate().isEqual(date.getDate());
    }
}
