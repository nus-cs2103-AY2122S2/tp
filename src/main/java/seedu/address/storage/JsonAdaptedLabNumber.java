package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.lab.Lab;

/**
 * Jackson-friendly version of {@link Lab}.
 */
class JsonAdaptedLabNumber {

    private final String labNumber;

    /**
     * Constructs a {@code JsonAdaptedLab} with the given {@code labNumber}.
     */
    @JsonCreator
    public JsonAdaptedLabNumber(String labNumber) {
        this.labNumber = labNumber;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedLabNumber(Lab source) {
        labNumber = String.valueOf(source.labNumber);
    }

    @JsonValue
    public String getLabNumber() {
        return labNumber;
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Lab} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lab.
     */
    public Lab toModelType() throws IllegalValueException {
        if (!Lab.isValidLab(labNumber)) {
            throw new IllegalValueException(Lab.MESSAGE_CONSTRAINTS);
        }
        return new Lab(labNumber);
    }

}
