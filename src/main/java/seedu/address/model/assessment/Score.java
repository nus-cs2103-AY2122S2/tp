package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the score for an Assessment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidScoreGivenFullMark(String, FullMark)}
 */
public class Score {
    public static final String MESSAGE_CONSTRAINTS =
            "The score should be a non-negative integer that is not greater than the full mark";
    public static final String VALIDATION_REGEX = "^(0|[1-9][0-9]?[0-9]?|1000)";
    public final int score;

    /**
     * Constructs a Score.
     * @param value the score.
     * @param fullMark the full marks of the assessment.
     */
    public Score(String value, FullMark fullMark) {
        requireNonNull(value);
        checkArgument(isValidScoreGivenFullMark(value, fullMark), MESSAGE_CONSTRAINTS);
        score = Integer.parseInt(value);
    }

    public static boolean isValidScore(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    public static boolean isValidScoreGivenFullMark(String value, FullMark fullMark) {
        return value.matches(VALIDATION_REGEX) && Integer.parseInt(value) <= fullMark.fullMark;
    }

    @Override
    public String toString() {
        return Integer.toString(score);
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof Score
                && score == ((Score) o).score);
    }

    @Override
    public int hashCode() {
        return score;
    }
}
