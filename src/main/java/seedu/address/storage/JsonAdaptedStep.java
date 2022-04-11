package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.recipe.Step;

/**
 * Jackson-friendly version of {@link Step}.
 */
public class JsonAdaptedStep {

    private final String stepValue;

    /**
     * Constructs a {@code JsonAdaptedStep} with the given {@code stepValue}.
     */
    @JsonCreator
    public JsonAdaptedStep(String stepValue) {
        this.stepValue = stepValue;
    }

    /**
     * Converts a given {@code Step} into this class for Jackson use.
     */
    public JsonAdaptedStep(Step source) {
        stepValue = source.value;
    }

    @JsonValue
    public String getStepValue() {
        return stepValue;
    }

    /**
     * Converts this Jackson-friendly adapted Step object into the model's {@code Step} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Step.
     */
    public Step toModelType() throws IllegalValueException {
        if (!Step.isValidStep(stepValue)) {
            throw new IllegalValueException(Step.MESSAGE_CONSTRAINTS);
        }
        return new Step(stepValue);
    }

}
