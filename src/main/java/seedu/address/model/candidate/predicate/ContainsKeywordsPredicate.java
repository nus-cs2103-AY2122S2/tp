package seedu.address.model.candidate.predicate;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.candidate.Candidate;

/**
 * Represents a predicate of whether the (@code Candidate) contains specific keywords.
 * Contains hidden internal logic and the ability to be executed.
 */
public abstract class ContainsKeywordsPredicate implements Predicate<Candidate> {
    protected final List<String> keywords;

    public ContainsKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);

        this.keywords = keywords;
    }

    @Override
    public boolean test(Candidate candidate) {
        requireNonNull(candidate);

        return keywords.stream()
                .anyMatch(keyword -> candidate.toString().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }


}
