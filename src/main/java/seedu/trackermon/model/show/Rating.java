package seedu.trackermon.model.show;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.util.AppUtil.checkArgument;

/***
 * Represents a Show's rating.
 * Guarantees: immutable; rating is valid as declared in {@link #isValidRating(int) and @link #isValidScore(String)}
 */
public class Rating implements Comparable<Rating> {

    public static final String INVALID_RATING = "Rating must be a whole number from 0 to 5";

    public static final int MAX_RATING = 5;

    public static final String VALIDATION_REGEX_RATING = "[0-5]";

    public final int rating;

    /**
     * Constructs a {@code rating} with the provided {@code int}.
     * @param rating {@code int}.
     */
    public Rating(int rating) {
        checkArgument(isValidRating(rating), INVALID_RATING);
        this.rating = rating;
    }

    /**
     * Constructs a {@code rating} with the provided {@code String}.
     * @param rating {@code String}.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        checkArgument(isValidRating(rating), INVALID_RATING);
        this.rating = Integer.parseInt(rating);
    }

    /**
     * Return true if a given {@code int} is a valid rating.
     */
    public static boolean isValidRating(int rating) {
        if (rating <= MAX_RATING && rating >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Return true if a given {@code String} is a valid rating.
     */
    public static boolean isValidRating(String rating) {
        return rating.matches(VALIDATION_REGEX_RATING);
    }

    /**
     * Return {@code String} representation of {@code Rating}.
     */
    @Override
    public String toString() {
        return Integer.toString(rating);
    }

    /**
     * Returns whether two objects are equal or share the same rating.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof Rating
                && rating == ((Rating) other).rating);
    }

    /**
     * Returns the hashcode of the {@code Rating}.
     */
    @Override
    public int hashCode() {
        return Integer.valueOf(rating).hashCode();
    }

    @Override
    public int compareTo(Rating other) {
        return Integer.compare(this.rating, other.rating);
    }
}
