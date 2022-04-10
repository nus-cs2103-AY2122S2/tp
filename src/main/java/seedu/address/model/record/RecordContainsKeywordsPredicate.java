package seedu.address.model.record;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Record}'s {@code InsuranceID} matches any of the keywords given.
 */
public class RecordContainsKeywordsPredicate implements Predicate<Record> {

    private final List<String> keywords;

    public RecordContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Record record) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        String.valueOf(record.getInsuranceID()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RecordContainsKeywordsPredicate) other).keywords)); // state check
    }
}
