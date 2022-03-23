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
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.service.Service;

/**
 * An Immutable TrackBeau that is serializable to JSON format.
 */
@JsonRootName(value = "trackBeau")
class JsonSerializableTrackBeau {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_SERVICE = "Services list contains duplicate service(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedService> services = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackBeau} with the given customers.
     */
    @JsonCreator
    public JsonSerializableTrackBeau(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                     @JsonProperty("services") List<JsonAdaptedService> services) {
        this.customers.addAll(customers);
        this.services.addAll(services);
    }

    /**
     * Converts a given {@code ReadOnlyTrackBeau} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTrackBeau}.
     */
    public JsonSerializableTrackBeau(ReadOnlyTrackBeau source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        services.addAll(source.getServiceList().stream().map(JsonAdaptedService::new).collect(Collectors.toList()));
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
        return trackBeau;
    }

}
