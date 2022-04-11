package seedu.address.model.buyer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Buyer}'s {@code Name} matches any of the keywords given.
 */
public class BuyerTagsContainsKeywordsPredicate implements Predicate<Buyer> {
    private final List<String> keywords;

    public BuyerTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Buyer buyer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(buyer.getTags().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BuyerTagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
