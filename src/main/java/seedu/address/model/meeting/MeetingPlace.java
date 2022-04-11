package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the location of the meeting.
 */
public class MeetingPlace implements Comparable<MeetingPlace> {
    public static final String MESSAGE_CONSTRAINTS = "Places can take any values, and it should not be blank.";

    /*
     * The first character of the place must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String place;

    /**
     * Constructs an {@code MeetingPlace}.
     *
     * @param place A valid place.
     */
    public MeetingPlace(String place) {
        requireNonNull(place);
        checkArgument(isValidMeetingPlace(place), MESSAGE_CONSTRAINTS);
        this.place = place;
    }

    /**
     * Returns true if a given string is a valid meeting place.
     */
    public static boolean isValidMeetingPlace(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.place;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingPlace // instanceof handles nulls
                & place.equals(((MeetingPlace) other).place)); // state check
    }

    @Override
    public int hashCode() {
        return place.hashCode();
    }

    @Override
    public int compareTo(MeetingPlace other) {
        return this.place.compareTo(other.place);
    }
}
