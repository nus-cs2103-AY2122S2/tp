package seedu.address.model.position;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represent a Position in HireLah.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Position {

    // Identity fields
    private final PositionName positionName;
    private final Description description;

    // Data fields
    private final PositionOpenings positionOpenings;
    private final Set<Requirement> requirements = new HashSet<>();

    /**
     * Every field must be present, not null and validated.
     */
    public Position(PositionName positionName, Description description, PositionOpenings positionOpenings,
                    Set<Requirement> requirements) {
        requireAllNonNull(positionName, description, positionOpenings, requirements);
        this.positionName = positionName;
        this.description = description;
        this.positionOpenings = positionOpenings;
        this.requirements.addAll(requirements);
    }

    public PositionName getPositionName() {
        return positionName;
    }

    public Description getDescription() {
        return description;
    }

    public PositionOpenings getPositionOpenings() {
        return positionOpenings;
    }

    /**
     * Returns an immutable requirements set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(requirements);
    }

    /**
     * Returns true if both positions have the same position name.
     * This defines a weaker notion of equality between two positions,
     * which allows for all positions to have a unique position name.
     */
    public boolean isSamePosition(Position otherPosition) {
        if (otherPosition == this) {
            return true;
        }
        return otherPosition != null
                && otherPosition.positionName.equals(positionName);
    }

    /**
     * Returns true if both positions have the same identity and data fields.
     * This defines a stronger notion of equality between two positions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;
        return otherPosition.getPositionName().equals(getPositionName())
                && otherPosition.getDescription().equals(getDescription())
                && otherPosition.getPositionOpenings().equals(getPositionOpenings())
                && otherPosition.getRequirements().equals(getRequirements());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(positionName, description, positionOpenings, requirements);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPositionName())
                .append("; Description: ")
                .append(getDescription())
                .append("; Openings: ")
                .append(getPositionOpenings());

        Set<Requirement> requirements = getRequirements();
        if (!requirements.isEmpty()) {
            builder.append("; Requirements: ");
            requirements.forEach(builder::append);
        }
        return builder.toString();
    }
}
