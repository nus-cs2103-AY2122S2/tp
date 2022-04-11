package seedu.trackermon.model.show;

import java.util.List;
import java.util.function.Predicate;

import seedu.trackermon.commons.util.StringUtil;

/**
 * Tests that a {@code Show}'s {@code Status} matches any of the keywords given.
 */
public class StatusContainsKeywordsPredicate implements Predicate<Show> {
    private final List<String> keywords;

    /**
     * Creates a predicate that contains the {@code Show} {@code Status}.
     * @param keywords the {@code Show} {@code Status}.
     */
    public StatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests for partial words in {@code Show}.
     * @param show {@code Show}.
     * @return returns true if partial word matches the {@code Show}, else returns false.
     */
    @Override
    public boolean test(Show show) {
        // Checks for fragmented words in status
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsFragmentedWordIgnoreCase(show.getStatus().toString(), keyword));
    }

    /**
     * Returns whether two objects are equal.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatusContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StatusContainsKeywordsPredicate) other).keywords)); // state check
    }

}
