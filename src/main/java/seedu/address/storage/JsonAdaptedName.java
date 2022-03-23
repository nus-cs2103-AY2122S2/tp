package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;

public class JsonAdaptedName {

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code Name}.
     */
    @JsonCreator
    public JsonAdaptedName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(Name source) {
        this.name = source.fullName;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted name object into the model's {@code Name} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted name.
     */
    public Name toModelType(String name) throws IllegalValueException {
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }
}
