package seedu.trackbeau.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;

/**
 * An Immutable TrackBeau that is serializable to JSON format.
 */
@JsonRootName(value = "trackBeau")
class JsonSerializableTrackBeau {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_SERVICE = "Services list contains duplicate service(s).";
    public static final String MESSAGE_DUPLICATE_BOOKING = "Bookings list contains duplicate booking(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedService> services = new ArrayList<>();
    private final List<JsonAdaptedBooking> bookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackBeau} with the given customers, services and bookings.
     */
    @JsonCreator
    public JsonSerializableTrackBeau(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                     @JsonProperty("services") List<JsonAdaptedService> services,
                                     @JsonProperty("bookings") List<JsonAdaptedBooking> bookings) {
        this.customers.addAll(customers);
        this.services.addAll(services);
        this.bookings.addAll(bookings);
    }

    /**
     * Converts a given {@code ReadOnlyTrackBeau} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackBeau}.
     */
    public JsonSerializableTrackBeau(ReadOnlyTrackBeau source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        services.addAll(source.getServiceList().stream().map(JsonAdaptedService::new).collect(Collectors.toList()));
        bookings.addAll(source.getBookingList().stream().map(JsonAdaptedBooking::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackBeau into the model's {@code TrackBeau} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackBeau toModelType() throws IllegalValueException {
        TrackBeau trackBeau = new TrackBeau();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (trackBeau.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            trackBeau.addCustomer(customer);
        }

        for (JsonAdaptedService jsonAdaptedService : services) {
            Service service = jsonAdaptedService.toModelType();
            if (trackBeau.hasService(service)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SERVICE);
            }
            trackBeau.addService(service);
        }

        for (JsonAdaptedBooking jsonAdaptedBooking : bookings) {
            Booking booking = jsonAdaptedBooking.toModelType();
            if (trackBeau.hasBooking(booking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOKING);
            }
            trackBeau.addBooking(booking);
        }
        return trackBeau;
    }
}
