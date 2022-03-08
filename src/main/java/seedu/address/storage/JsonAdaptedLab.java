package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Lab}.
 */
class JsonAdaptedLab {

    private final String labNumber;
    //private final String labStatus;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code labNumber}.
     */
    @JsonCreator
    public JsonAdaptedLab(String labNumber) {
        this.labNumber = labNumber;
        //this.labStatus = labStatus;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedLab(Lab source) {
        labNumber = String.valueOf(source.labNumber);
        //labStatus = source.labStatus.toString();
    }

    @JsonValue
    public String getLabNumber() {
        return labNumber;
    }

//    @JsonValue
//    public String getLabStatus() {
//        return labStatus;
//    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Lab} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lab.
     */
    public Lab toModelType() throws IllegalValueException {
        if (!Lab.isValidLab(labNumber)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Lab(labNumber);
    }

}
