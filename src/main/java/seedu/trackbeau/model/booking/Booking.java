package seedu.trackbeau.model.booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.uniquelist.UniqueListItem;

public class Booking implements UniqueListItem {
    //private final Service Service;
    private final Customer customer;
    private final LocalDateTime startDateTime;

    /**
     * Every field must be present and not null.
     */
    public Booking(Customer customer, LocalDateTime startDateTime) {
        this.customer = customer;
        this.startDateTime = startDateTime;
    }

    public String getCustomerName() {
        return customer.getName().fullName;
    }

    public String getCustomerPhone() {
        return customer.getPhone().value;
    }

    public String getDateTime() {
        return startDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' h:mm a"));
    }

    /**
     * Returns true if both bookings have the same name.
     * This defines a weaker notion of equality between two bookings.
     */
    @Override
    public boolean isSameItem(UniqueListItem otherBooking) {
        return otherBooking == this;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
            .append(getCustomerName())
            .append("; Appointment Time: ")
            .append(getDateTime())
            .append(";");

        return builder.toString();
    }
}
