package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lineup.LineupName;

/**
 * Jackson-friendly version of {@link LineupName}
 */
public class JsonAdaptedLineupName {

    private final String lineupName;

    /**
     * Constructs a {@code JsonAdaptedLineupName} with the given {@code lineupName}.
     */
    @JsonCreator
    public JsonAdaptedLineupName(String lineupName) {
        this.lineupName = lineupName;
    }

    /**
     * Converts a given {@code LineupName} into this class for Jackson use.
     */
    public JsonAdaptedLineupName(LineupName lineupName) {
        this.lineupName = lineupName.lineupName;
    }

    @JsonValue
    public String getLineupName() {
        return lineupName;
    }

    /**
     * Returns a {@code LineupName}
     */
    public LineupName toModelType() throws IllegalValueException {
        if (!LineupName.isValidLineupName(lineupName)) {
            throw new IllegalValueException(LineupName.MESSAGE_CONSTRAINTS);
        }
        return new LineupName(lineupName);
    }
}
