package seedu.contax.model.chrono;

import java.time.LocalDateTime;

/**
 * Represents an object that can be compared chronologically using a {@code LocalDateTime} associated with it.
 */
public interface TemporalComparable extends Comparable<TemporalComparable> {
    /**
     * Returns the representative {@code LocalDateTime} for use in comparison.
     *
     * @return A LocalDateTime used for comparison.
     */
    LocalDateTime getComparableDateTime();
}
