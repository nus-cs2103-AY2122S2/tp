package seedu.trackermon.model.show;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.util.AppUtil.checkArgument;


public class Rating {

    public static final String INVALID_RATING = "Rating should be between 0 to 10";

    public static final Integer FULL_RATING = 10;

    private final Integer rating;

    /**
     * Constructs a {@code rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(int rating) {
        requireNonNull(rating);
        checkArgument(isValidScore(rating), INVALID_RATING);
        this.rating = rating;
    }

    /**
     * Return true if a given rating is a valid rating.
     */
    public static boolean isValidScore(int rating) {
        if (rating <= 10 && rating >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Return true if a given rating is a valid rating.
     */
    public static boolean isValidScore(String rating) {
        try {
            int parsedScore = Integer.parseInt(rating);
            return isValidScore(parsedScore);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(rating);
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof Rating
                && rating.equals(((Rating) other).rating));
    }

    public String stringRating() {
        return rating.toString();
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }
}
