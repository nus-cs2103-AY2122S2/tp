package seedu.trackermon.model.show;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.util.AppUtil.checkArgument;


public class Rating {

    public static final String INVALID_RATING = "Rating should be between 0 to 10";

    public static final Integer FULL_RATING = 10;

    private final Integer score;

    /**
     * Constructs a {@code score}.
     *
     * @param score A valid score.
     */
    public Rating(int score) {
        requireNonNull(score);
        checkArgument(isValidScore(score), INVALID_RATING);
        this.score = score;
    }

    /**
     * Return true if a given score is a valid score.
     */
    public static boolean isValidScore(int score) {
        if (score <= 10 && score >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Return true if a given score is a valid score.
     */
    public static boolean isValidScore(String score) {
        try {
            int parsedScore = Integer.parseInt(score);
            return isValidScore(parsedScore);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof Rating
                && score.equals(((Rating) other).score));
    }

    @Override
    public int hashCode() {
        return score.hashCode();
    }
}
