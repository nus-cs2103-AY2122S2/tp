package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lab.Lab;
import seedu.address.model.student.exceptions.InvalidLabStatusException;

/**
 * Jackson-friendly version of {@link Lab}.
 */
class JsonAdaptedLab {

    private final String labNumber;
    private final String labStatus;
    private final String labMark;

    /**
     * Constructs a {@code JsonAdaptedLab} with the given {@code labNumber}.
     */
    @JsonCreator
    public JsonAdaptedLab(@JsonProperty("number") String labNumber, @JsonProperty("status") String labStatus,
                          @JsonProperty("labMark") String labMark) {
        this.labNumber = labNumber;
        this.labStatus = labStatus;
        this.labMark = labMark;
    }

    /**
     * Converts a given {@code Lab} into this class for Jackson use.
     */
    public JsonAdaptedLab(Lab source) {
        labNumber = String.valueOf(source.labNumber);
        labStatus = source.labStatus.name();
        labMark = source.labMark.toString();
    }

    public String getLabNumber() {
        return labNumber;
    }

    public String getLabStatus() {
        return labStatus;
    }

    public String getLabMark() {
        return labMark;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Lab} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lab.
     */
    public Lab toModelType() throws IllegalValueException {
        if (labNumber == null || !Lab.isValidLab(labNumber)) {
            throw new IllegalValueException(Lab.MESSAGE_CONSTRAINTS);
        }
        try {
            return new Lab(labNumber).of(orElse(labStatus, "UNSUBMITTED"), orElse(labMark, "Unknown"));
        } catch (InvalidLabStatusException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String orElse(String str, String alt) {
        if (str == null) {
            return alt;
        }
        return str;
    }

}
