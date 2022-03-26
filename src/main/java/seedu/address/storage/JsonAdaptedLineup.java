package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;

/**
 * Jackson-friendly version of {@link Lineup}.
 */
public class JsonAdaptedLineup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Lineup's %s field is missing!";

    private final String lineupName;

    /**
     * Constructs a {@code JsonAdaptedLineup} with the given lineup details.
     */
    @JsonCreator
    public JsonAdaptedLineup(@JsonProperty("lineupName") String lineupName) {
        this.lineupName = lineupName;
    }

    /**
     * Converts a given {@code Lineup} into this class for Jackson use.
     */
    public JsonAdaptedLineup(Lineup source) {
        this.lineupName = source.getLineupName().lineupName;
    }

    /**
     * Converts this Jackson-friendly adapted lineup object into the model's {@code Lineup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Lineup toModelType() throws IllegalValueException {

        if (lineupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LineupName.class.getSimpleName()));
        }

        final LineupName modelLineupName = new LineupName(lineupName);

        return new Lineup(modelLineupName);
    }

}
