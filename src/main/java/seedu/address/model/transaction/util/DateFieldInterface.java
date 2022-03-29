package seedu.address.model.transaction.util;

import java.time.LocalDate;

public interface DateFieldInterface {
    LocalDate getDate();

    /**
     * Returns whether the date is not after another particular
     * date.
     */
    default boolean isBefore(DateFieldInterface date) {
        return getDate().isBefore(date.getDate())
                || getDate().isEqual(date.getDate());
    }
}
