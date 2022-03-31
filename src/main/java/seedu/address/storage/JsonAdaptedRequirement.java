package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Requirement;


public class JsonAdaptedRequirement {

    private final String requirementText;

    /**
     * Constructs a {@code JsonAdaptedRequirement} with the given {@code requirementText}.
     */
    @JsonCreator
    public JsonAdaptedRequirement(String requirementText) {
        this.requirementText = requirementText;
    }

    /**
     * Converts a given {@code Requirement} into this class for Jackson use.
     */
    public JsonAdaptedRequirement(Requirement source) {
        requirementText = source.requirementText;
    }

    @JsonValue
    public String getRequirementText() {
        return requirementText;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Requirement} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Requirement toModelType() throws IllegalValueException {
        if (!Requirement.isValidRequirementText(requirementText)) {
            throw new IllegalValueException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(requirementText);
    }

}
