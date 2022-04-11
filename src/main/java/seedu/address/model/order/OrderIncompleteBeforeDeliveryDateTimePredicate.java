package seedu.address.model.order;

import java.util.function.Predicate;

/**
 * Tests that a {@code Order}'s {@code DeliveryDateTime} is before the given date and its
 * {@code Complete} status is incomplete.
 */
public class OrderIncompleteBeforeDeliveryDateTimePredicate implements Predicate<Order> {
    private final DeliveryDateTime deliveryDateTime;

    public OrderIncompleteBeforeDeliveryDateTimePredicate(DeliveryDateTime deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    @Override
    public boolean test(Order order) {
        return !order.isComplete()
                && !order.getDeliveryDateTimeValue().isAfter(deliveryDateTime.getValue());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderIncompleteBeforeDeliveryDateTimePredicate // instanceof handles nulls
                && deliveryDateTime.equals(((OrderIncompleteBeforeDeliveryDateTimePredicate) other).deliveryDateTime));
    }
}
