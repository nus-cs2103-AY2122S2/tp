package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Position objects.
 */
public class PositionBuilder {

    public static final String DEFAULT_POSITION_NAME = "Senior Front-End Engineer";
    public static final String DEFAULT_DESCRIPTION = "Job is remote with great medical benefits!";
    public static final String DEFAULT_POSITION_OPENINGS = "3";

    // Identity fields
    private PositionName positionName;
    private Description description;

    // Data fields
    private PositionOpenings positionOpenings;
    private Set<Requirement> requirements;

    /**
     * Creates a {@code PositionBuilder} with the default details.
     */
    public PositionBuilder() {
        positionName = new PositionName(DEFAULT_POSITION_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        positionOpenings = new PositionOpenings(DEFAULT_POSITION_OPENINGS);
        requirements = new HashSet<>();
    }

    /**
     * Initializes the PositionBuilder with the data of {@code positionToCopy}.
     */
    public PositionBuilder(Position positionToCopy) {
        positionName = positionToCopy.getPositionName();
        description = positionToCopy.getDescription();
        positionOpenings = positionToCopy.getPositionOpenings();
        requirements = new HashSet<>(positionToCopy.getRequirements());
    }

    /**
     * Sets the {@code PositionName} of the {@code Position} that we are building.
     */
    public PositionBuilder withPositionName(String positionName) {
        this.positionName = new PositionName(positionName);
        return this;
    }

    /**
     * Parses the {@code requirements} into a {@code Set<Requirement>} and set it to the {@code Position}
     * that we are building.
     */
    public PositionBuilder withRequirements(String... requirements) {
        this.requirements = SampleDataUtil.getRequirementSet(requirements);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Position} that we are building.
     */
    public PositionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code PositionOpenings} of the {@code Position} that we are building.
     */
    public PositionBuilder withPositionOpenings(String positionOpenings) {
        this.positionOpenings = new PositionOpenings(positionOpenings);
        return this;
    }

    public Position build() {
        return new Position(positionName, description, positionOpenings, requirements);
    }
}
