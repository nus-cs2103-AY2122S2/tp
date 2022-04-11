package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Deadline;

class JsonAdaptedDeadline {
    private final String description;
    private final String date;

    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("description") String description,
                               @JsonProperty("date") String date) {
        this.description = description;
        this.date = date;
    }

    public JsonAdaptedDeadline(Deadline source) {
        description = source.description;
        date = source.value;
    }

    public Deadline toModelType() throws IllegalValueException {
        if (!Deadline.isValidDeadline(description, date)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(description, date);
    }
}
