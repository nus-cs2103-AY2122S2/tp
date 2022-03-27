package seedu.address.model.candidate.predicate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.candidate.Candidate;

/**
 * Tests that a {@code Candidate}'s {@code Availability} matches any of the keywords given.
 */
public class AvailabilityContainsKeywordsPredicate extends ContainsKeywordsPredicate implements Predicate<Candidate> {
    public static final String[] DAYS_IN_FULL = { "", "MON", "TUE", "WED", "THU", "FRI" };
    private final List<String> keywords;

    /**
     * Creates a new {@link AvailabilityContainsKeywordsPredicate} object with the
     * {@link AvailabilityContainsKeywordsPredicate#keywords} initialised.
     * @param keywords contain keyword(s) to find in {@code Candidate}'s {@code Availability}.
     */
    public AvailabilityContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    /**
     * Tests if any part of {@code Candidate}'s {@code Availability} matches any of the specified
     * {@link AvailabilityContainsKeywordsPredicate#keywords}.
     * @param candidate object to retrieve the {@code Availability}.
     * @return true if a match is found, and false otherwise.
     */
    @Override
    public boolean test(Candidate candidate) {
        String availability = candidate.getAvailability().toString();
        int[] availArr = Arrays.stream(availability.split(",")).mapToInt((Integer::parseInt)).toArray();
        HashMap<String, Integer> availMap = new HashMap<>();

        for (Integer i: availArr) {
            availMap.put(DAYS_IN_FULL[i], i);
        }

        return keywords.stream().anyMatch(keyword -> availMap.containsKey(keyword.toUpperCase()));
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link AvailabilityContainsKeywordsPredicate#keywords}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link AvailabilityContainsKeywordsPredicate} with the same
     * {@link AvailabilityContainsKeywordsPredicate#keywords}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AvailabilityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AvailabilityContainsKeywordsPredicate) other).keywords)); // state check
    }
}
