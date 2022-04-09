package seedu.trackermon.model.show;

import java.util.List;
import java.util.function.Predicate;

import seedu.trackermon.commons.util.StringUtil;

/**
 * Tests that a {@code Show}'s {@code Rating} matches any of the keywords given.
 */
public class RatingContainsKeywordsPredicate implements Predicate<Show> {
    private final List<String> keywords;

    /**
     * Creates a predicate that contains the {@code Show} {@code Rating}.
     * @param keywords the {@code Show} {@code Rating}.
     */
    public RatingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests for partial words in {@code Show}.
     * @param show {@code Show}.
     * @return returns true if partial word matches the {@code Show}, else returns false.
     */
    @Override
    public boolean test(Show show) {
        // Checks for fragmented words in rating
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsFragmentedWordIgnoreCase(show.getRating().toString(), keyword));
    }

    /**
     * Returns whether two objects are equal.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RatingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RatingContainsKeywordsPredicate) other).keywords)); // state check
    }

}
