package seedu.address.model.order;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Order}'s {@code Date} matches any of the dates given.
 */
public class OrderDeliveredAndFromDatePredicate implements Predicate<Order> {
    private final List<LocalDate> dates;

    public OrderDeliveredAndFromDatePredicate(List<LocalDate> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Order order) {
        return dates.stream()
                .anyMatch(date -> date.isEqual(order.getTime().toLocalDate())
                        && order.getStatus().equals(OrderStatus.DELIVERED));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrderDeliveredAndFromDatePredicate)
                && dates.equals(((OrderDeliveredAndFromDatePredicate) other).dates);
    }
}

