package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabMark;

/**
 * Jackson-friendly version of {@link LabMark}.
 */
class JsonAdaptedLabMark {

    private final String labMark;

    /**
     * Constructs a {@code JsonAdaptedLabMark} with the given {@code labMark}.
     */
    @JsonCreator
    public JsonAdaptedLabMark(String labMark) {
        this.labMark = labMark;
    }

    /**
     * Converts a given {@code Lab} into this class for Jackson use.
     */
    public JsonAdaptedLabMark(Lab source) {
        labMark = source.labMark.toString();
    }

    @JsonValue
    public String getLabMark() {
        return labMark;
    }

}
