package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;

public class JsonAdaptedEmail {

    private final String email;

    /**
     * Constructs a {@code JsonAdaptedEmail} with the given {@code email}.
     */
    @JsonCreator
    public JsonAdaptedEmail(String email) {
        this.email = email;
    }

    /**
     * Converts a given {@code Email} into this class for Jackson use.
     */
    public JsonAdaptedEmail(Email source) {
        email = source.email;
    }

    @JsonValue
    public String getEmail() {
        return email;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Email} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted email.
     */
    public Email toModelType() throws IllegalValueException {
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

}

