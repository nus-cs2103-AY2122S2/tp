package seedu.address.model.transaction;

import java.time.LocalDate;

public interface DateInterface {
    LocalDate getDate();

    default boolean isBefore(DateInterface date) {
        return getDate().isBefore(date.getDate()) ||
                getDate().isEqual(date.getDate());
    }
}
