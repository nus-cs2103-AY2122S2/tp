package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Pronoun;

public class JsonAdaptedPronoun {

    private final String pronoun;

    /**
     * Constructs a {@code JsonAdaptedPronoun} with the given {@code pronoun}.
     */
    @JsonCreator
    public JsonAdaptedPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    /**
     * Converts a given {@code Pronoun} into this class for Jackson use.
     */
    public JsonAdaptedPronoun(Pronoun source) {
        pronoun = source.pronoun;
    }

    @JsonValue
    public String getPronoun() {
        return pronoun;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Pronoun} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted pronoun.
     */
    public Pronoun toModelType() throws IllegalValueException {
        if (!Pronoun.isValidPronoun(pronoun)) {
            throw new IllegalValueException(Pronoun.MESSAGE_CONSTRAINTS);
        }
        return new Pronoun(pronoun);
    }

}

