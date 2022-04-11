package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job listings' position: full-time or part-time.
 * Guarantees: immutable; is valid as declared in {@link #validPosition(String)}
 */
public class Position {
    public static final String MESSAGE_CONSTRAINTS = "Job position should be 'ft' (full-time) or 'pt' (part-time).";

    private static final String PREFIX_POSITION_FULLTIME = "ft";
    private static final String PREFIX_POSITION_PARTTIME = "pt";

    private static final String POSITION_FULLTIME = "full-time";
    private static final String POSITION_PARTTIME = "part-time";

    public final String position;

    /**
     * Instantiates a new Position.
     *
     * @param position the position
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(validPosition(position), MESSAGE_CONSTRAINTS);
        this.position = position.toLowerCase();
    }

    /**
     * Checks if a given string is a valid position.
     *
     * @param position the position
     * @return if the given position is valid
     */
    public static boolean validPosition(String position) {
        return position.toLowerCase().matches(PREFIX_POSITION_FULLTIME + "|" + PREFIX_POSITION_PARTTIME);
    }

    @Override
    public String toString() {
        return position.equals(PREFIX_POSITION_FULLTIME)
                ? POSITION_FULLTIME
                : POSITION_PARTTIME;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.job.Position // instanceof handles nulls
                && position.equals(((seedu.address.model.job.Position) other).position)); // state check
    }
}
