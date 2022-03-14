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

/**
 * An Immutable TrackBeau that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTrackBeau {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate customer(s).";

    private final List<JsonAdaptedCustomer> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTrackBeau(@JsonProperty("persons") List<JsonAdaptedCustomer> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyTrackBeau} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTrackBeau(ReadOnlyTrackBeau source) {
        persons.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this trackbeau book into the model's {@code TrackBeau} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TrackBeau toModelType() throws IllegalValueException {
        TrackBeau trackBeau = new TrackBeau();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : persons) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (trackBeau.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            trackBeau.addCustomer(customer);
        }
        return trackBeau;
    }

}
