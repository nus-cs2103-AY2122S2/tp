package seedu.trackbeau.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.booking.BookingDateTime;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.service.ServiceName;

/**
 * Jackson-friendly version of {@link Booking}.
 */
class JsonAdaptedBooking {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String name;
    private final String phone;
    private final String service;
    private final String bookingDateTime;

    /**
     * Constructs a {@code JsonAdaptedBooking} with the given Booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("service") String service,
                              @JsonProperty("bookingDateTime") String bookingDateTime) {
        this.name = name;
        this.phone = phone;
        this.service = service;
        this.bookingDateTime = bookingDateTime;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        name = source.getCustomerName().fullName;
        phone = source.getCustomerPhone().value;
        service = source.getServiceName().fullName;
        bookingDateTime = source.getBookingDateTime().toString();
    }

    Name getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    Phone getModelPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    ServiceName getModelServiceName() throws IllegalValueException {
        if (service == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ServiceName.class.getSimpleName()));
        }

        if (!ServiceName.isValidName(service)) {
            throw new IllegalValueException(ServiceName.MESSAGE_CONSTRAINTS);
        }
        return new ServiceName(service);
    }

    BookingDateTime getModelBookingDateTime() throws IllegalValueException {
        if (bookingDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    BookingDateTime.class.getSimpleName()));
        }

        if (!BookingDateTime.isValidBookingDateTime(bookingDateTime)) {
            throw new IllegalValueException(BookingDateTime.MESSAGE_CONSTRAINTS);
        }
        return new BookingDateTime(bookingDateTime);
    }

    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Booking.
     */
    public Booking toModelType() throws IllegalValueException {
        final Name modelName = this.getModelName();
        final Phone modelPhone = this.getModelPhone();
        final ServiceName modelServiceName = this.getModelServiceName();
        final BookingDateTime modelBookingDateTime = this.getModelBookingDateTime();

        return new Booking(modelName, modelPhone, modelServiceName, modelBookingDateTime);
    }
}
