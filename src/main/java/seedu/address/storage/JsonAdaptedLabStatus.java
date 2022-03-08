package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.person.lab.Lab;

/**
 * Jackson-friendly version of {@link Lab}.
 */
class JsonAdaptedLabStatus {

    private final String labStatus;

    /**
     * Constructs a {@code JsonAdaptedLab} with the given {@code labNumber}.
     */
    @JsonCreator
    public JsonAdaptedLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedLabStatus(Lab source) {
        labStatus = source.labStatus.name();
    }

    @JsonValue
    public String getLabStatus() {
        return labStatus;
    }

}
