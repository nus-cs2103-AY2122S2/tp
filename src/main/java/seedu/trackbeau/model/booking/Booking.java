package seedu.trackbeau.model.booking;

import static seedu.trackbeau.commons.util.CollectionUtil.requireAllNonNull;

import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.service.ServiceName;
import seedu.trackbeau.model.uniquelist.UniqueListItem;

/**
 * Represents a Booking in trackBeau.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking implements UniqueListItem {
    private final Name customerName;
    private final Phone customerPhone;
    private final ServiceName serviceName;
    private final BookingDateTime bookingDateTime;

    /**
     * Every field must be present and not null.
     */
    public Booking(Name customerName, Phone customerPhone, ServiceName serviceName, BookingDateTime bookingDateTime) {
        requireAllNonNull(customerName, customerPhone, serviceName, bookingDateTime);
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.serviceName = serviceName;
        this.bookingDateTime = bookingDateTime;
    }

    public Name getCustomerName() {
        return customerName;
    }

    public Phone getCustomerPhone() {
        return customerPhone;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public BookingDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    /**
     * Returns true if both bookings is the same as define by {@code equals}.
     */
    @Override
    public boolean isSameItem(UniqueListItem otherBooking) {
        return this.equals(otherBooking);
    }

    /**
     * Returns true if both bookings have the same fields.
     * This defines a strong notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return otherBooking.getCustomerName().equals(getCustomerName())
                && otherBooking.getCustomerPhone().equals(getCustomerPhone())
                && otherBooking.getServiceName().equals(getServiceName())
                && otherBooking.getBookingDateTime().equals(getBookingDateTime());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCustomerName())
                .append("; Phone: ")
                .append(getCustomerPhone())
                .append("; Service: ")
                .append(getServiceName())
                .append("; Appointment DateTime: ")
                .append(getBookingDateTime());

        return builder.toString();
    }
}
