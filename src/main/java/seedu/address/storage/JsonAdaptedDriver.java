package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;

/**
 * Jackson-friendly version of {@link Driver}.
 */
class JsonAdaptedDriver {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Driver's %s field is missing!";

    private final String name;
    private final String phone;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedDriver} with the given dish details.
     */
    @JsonCreator
    public JsonAdaptedDriver(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("status") String status) {
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    /**
     * Converts a given {@code Driver} into this class for Jackson use.
     */
    public JsonAdaptedDriver(Driver source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        status = source.getStatus().name();
    }

    /**
     * Converts this Jackson-friendly adapted driver object into the model's {@code Driver} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted driver.
     */
    public Driver toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NameDriver.class.getSimpleName()));
        }
        if (!NameDriver.isValidName(name)) {
            throw new IllegalValueException(NameDriver.MESSAGE_CONSTRAINTS);
        }

        final NameDriver modelName = new NameDriver(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PhoneDriver.class.getSimpleName()));
        }
        if (!PhoneDriver.isValidPhone(phone)) {
            throw new IllegalValueException(PhoneDriver.MESSAGE_CONSTRAINTS);
        }

        final PhoneDriver modelPhone = new PhoneDriver(phone);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }

        final DriverStatus modelStatus = DriverStatus.valueOf(status);

        return new Driver(modelName, modelPhone, modelStatus);
    }

}

