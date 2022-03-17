package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Log;
import seedu.address.model.person.LogName;


/**
 * Jackson-friendly version of {@link Log}
 */
public class JsonAdaptedLog {
    private final String title;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedLog} with the given {@code title} and {@code desccription}.
     * Assumes that title and description are both valid and non-null.
     */
    @JsonCreator
    public JsonAdaptedLog(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Constructs a {@code JsonAdaptedLog} with the given {@code Log}.
     */
    public JsonAdaptedLog(Log log) {
        this.title = log.getTitle().toString();
        this.description = log.getDescription().toString();
    }

    /**
     * Converts a {@code JsonAdoptedLog} to a {@code Log} object.
     *
     * @throws IllegalValueException if constructed {@code Log} contains
     *                               illegal values.
     */
    public Log toModelType() throws IllegalValueException {
        if (!LogName.isValidLogName(this.title)) {
            throw new IllegalValueException(LogName.MESSAGE_CONSTRAINTS);
        }
        return new Log(this.title, this.description);
    }
}
