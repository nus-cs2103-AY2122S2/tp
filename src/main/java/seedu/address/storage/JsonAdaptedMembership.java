package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Membership;

/**
 * Jackson-friendly version of {@link Membership}.
 */
class JsonAdaptedMembership {

    private final String tagName;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedMembership} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedMembership(String tagName) {
        String[] split = tagName.split("SINCE");
        this.tagName = split[0];
        this.date = split[1];
    }

    /**
     * Converts a given {@code Membership} into this class for Jackson use.
     */
    public JsonAdaptedMembership(Membership source) {
        tagName = source.getValue();
        date = source.getDate().toString();
    }

    @JsonValue
    public String getTagName() {
        return tagName + "SINCE" + date;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Membership toModelType() throws IllegalValueException {
        if (!Membership.isValidName(tagName)) {
            throw new IllegalValueException(Membership.MESSAGE_CONSTRAINTS);
        }
        if (date == null) {
            return new Membership(tagName);
        }

        if (!Membership.isValidDate(date)) {
            throw new IllegalValueException(Membership.MESSAGE_DATE_CONSTRAINTS);
        }
        return new Membership(tagName, LocalDate.parse(date));
    }

}
