package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Jackson-friendly version of {@link TutorialGroup}.
 */
class JsonAdaptedTutorialGroup {

    private final String tutorialGroupName;

    /**
     * Constructs a {@code JsonAdaptedTutorialGroup} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTutorialGroup(String tutorialGroupName) {
        this.tutorialGroupName = tutorialGroupName;
    }

    /**
     * Converts a given {@code TutorialGroup} into this class for Jackson use.
     */
    public JsonAdaptedTutorialGroup(TutorialGroup source) {
        tutorialGroupName = source.tutorialGroupName;
    }

    @JsonValue
    public String getTutorialGroupName() {
        return tutorialGroupName;
    }

    /**
     * Converts this Jackson-friendly adapted tutorial group object into the model's {@code TutorialGroup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutorial group.
     */
    public TutorialGroup toModelType() throws IllegalValueException {
        if (!TutorialGroup.isValidTutorialGroupName(tutorialGroupName)) {
            throw new IllegalValueException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        return new TutorialGroup(tutorialGroupName);
    }

}
