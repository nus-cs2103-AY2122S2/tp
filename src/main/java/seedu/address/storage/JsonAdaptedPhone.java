package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Phone;

public class JsonAdaptedPhone {

    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedPhone} with the given {@code phone}.
     */
    @JsonCreator
    public JsonAdaptedPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Converts a given {@code Phone} into this class for Jackson use.
     */
    public JsonAdaptedPhone(Phone source) {
        phone = source.phone;
    }

    @JsonValue
    public String getPhone() {
        return phone;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Phone} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted phone.
     */
    public Phone toModelType() throws IllegalValueException {
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

}

