package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.activity.Activity;

/**
 * Jackson-friendly version of {@link Activity}.
 */
class JsonAdaptedActivity {

    private final String activityName;

    /**
     * Constructs a {@code JsonAdaptedActivity} with the given {@code activityName}.
     */
    @JsonCreator
    public JsonAdaptedActivity(String activityName) {
        this.activityName = activityName;
    }

    /**
     * Converts a given {@code Activity} into this class for Jackson use.
     */
    public JsonAdaptedActivity(Activity source) {
        activityName = source.activityName;
    }

    @JsonValue
    public String getActivityName() {
        return activityName;
    }

    /**
     * Converts this Jackson-friendly adapted activity object into the model's {@code Activity} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted activity.
     */
    public Activity toModelType() throws IllegalValueException {
        if (!Activity.isValidActivityName(activityName)) {
            throw new IllegalValueException(Activity.MESSAGE_CONSTRAINTS);
        }
        return new Activity(activityName);
    }

}
