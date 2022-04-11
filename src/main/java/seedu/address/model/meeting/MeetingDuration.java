package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents a Meeting's duration in the meeting list.
 * Guarantees: immutable;
 */
public class MeetingDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "Duration should be a decimal number (4dp) "
            + "given in hours, and range from 1 minute to 24 hours inclusive";
    public static final String VALIDATION_REGEX = "^0*[0-9]{1,2}(\\.[0-9]{1,4})?$";
    public final double duration;

    /**
     * Constructs a {@code MeetingDuration}
     *
     * @Param duration A valid duration.
     */
    public MeetingDuration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = Double.parseDouble(duration);
    }

    /**
     * Returns true if a given string is able to be parsed into
     * a float and the resulting float is less than or equal to 24 and non-negative.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} is a valid float.
     */
    public static boolean isValidDuration(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            final double durationToTest = Double.parseDouble(test);
            return durationToTest >= ((1.0 / 60.0)) && durationToTest <= 24;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the endDateTime given the startDateTime.
     *
     * @param startDateTime The {@code LocalDateTime} to add the duration to.
     * @return The resulting LocalDateTime after adding the duration to the startDateTime.
     */
    public LocalDateTime getEndDateTime(LocalDateTime startDateTime) {
        final int durationInMinutes = (int) (duration * 60);
        return startDateTime.plusMinutes(durationInMinutes);
    }

    @Override
    public String toString() {
        return Double.toString(duration);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDuration // instanceof handles nulls
                && duration == (((MeetingDuration) other).duration)); // state check
    }
}
