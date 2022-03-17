package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.FriendName;

/**
 * Jackson-friendly version of {@link FriendName}.
 */
class JsonAdaptedName {

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedName} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedName(String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedName(FriendName source) {
        name = source.fullName;
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
    public FriendName toModelType() throws IllegalValueException {
        if (!FriendName.isValidFriendName(name)) {
            throw new IllegalValueException(FriendName.MESSAGE_CONSTRAINTS);
        }
        return new FriendName(name);
    }
}
