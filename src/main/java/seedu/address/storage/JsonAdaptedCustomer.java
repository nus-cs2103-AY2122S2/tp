package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.AddressCustomer;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomer;
import seedu.address.model.customer.PhoneCustomer;

/**
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Customer's %s field is missing!";

    private final String name;
    private final String phone;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("address") String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;

    }

    /**
     * Converts a given {@code Customer} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        address = source.getAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameCustomer.class.getSimpleName()));
        }
        if (!NameCustomer.isValidName(name)) {
            throw new IllegalValueException(NameCustomer.MESSAGE_CONSTRAINTS);
        }
        final NameCustomer modelNameCustomer = new NameCustomer(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneCustomer.class.getSimpleName()));
        }
        if (!PhoneCustomer.isValidPhone(phone)) {
            throw new IllegalValueException(PhoneCustomer.MESSAGE_CONSTRAINTS);
        }
        final PhoneCustomer modelPhoneCustomer = new PhoneCustomer(phone);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AddressCustomer.class.getSimpleName()));
        }
        if (!AddressCustomer.isValidAddress(address)) {
            throw new IllegalValueException(AddressCustomer.MESSAGE_CONSTRAINTS);
        }
        final AddressCustomer modelAddressCustomer = new AddressCustomer(address);

        return new Customer(modelNameCustomer, modelPhoneCustomer, modelAddressCustomer);
    }

}
