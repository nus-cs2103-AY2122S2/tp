package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;

public class JsonAdaptedAddress {

    private final String address;

    /**
     * Constructs a {@code JsonAdaptedAddress} with the given {@code address}.
     */
    @JsonCreator
    public JsonAdaptedAddress(String address) {
        this.address = address;
    }

    /**
     * Converts a given {@code Address} into this class for Jackson use.
     */
    public JsonAdaptedAddress(Address source) {
        address = source.address;
    }

    @JsonValue
    public String getAddress() {
        return address;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Address} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted address.
     */
    public Address toModelType() throws IllegalValueException {
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

}
