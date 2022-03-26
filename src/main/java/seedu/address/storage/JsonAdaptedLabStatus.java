package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabStatus;

/**
 * Jackson-friendly version of {@link LabStatus}.
 */
class JsonAdaptedLabStatus {

    private final String labStatus;

    /**
     * Constructs a {@code JsonAdaptedLabStatus} with the given {@code labNumber}.
     */
    @JsonCreator
    public JsonAdaptedLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    /**
     * Converts a given {@code Lab} into this class for Jackson use.
     */
    public JsonAdaptedLabStatus(Lab source) {
        labStatus = source.labStatus.name();
    }

    @JsonValue
    public String getLabStatus() {
        return labStatus;
    }

}
