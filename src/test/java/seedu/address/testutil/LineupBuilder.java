package seedu.address.testutil;

import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.lineup.LineupPlayersList;
import seedu.address.model.person.Person;

import static seedu.address.testutil.TypicalPersons.BENSON;

/**
 * A utility class to help with building Lineup objects.
 */
public class LineupBuilder {
    public static final String DEFAULT_LINEUP_NAME = "I AM A LINEUP";

    private LineupName lineupName;

    /**
     * Create a {@code LineupBuilder} with the default details.
     */
    public LineupBuilder() {
        lineupName = new LineupName(DEFAULT_LINEUP_NAME);
    }

    /**
     * Initializes the LineupBuilder with the data of {@code lineupToCopy}/
     */
    public LineupBuilder(Lineup lineupToCopy) {
        lineupName = lineupToCopy.getLineupName();
    }

    /**
     * Sets the {@code LineupName} of the {@code Person} that we are building.
     */
    public LineupBuilder withLineupName(String lineupName) {
        this.lineupName = new LineupName(lineupName);
        return this;
    }

    public Lineup build() {
        return new Lineup(lineupName);
    }
}
