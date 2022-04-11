package seedu.address.model.buyer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Buyer}'s {@code Phone} matches any of the keywords given.
 */
public class BuyerPhoneContainsKeywordsPredicate implements Predicate<Buyer> {
    private final List<String> keywords;

    public BuyerPhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Buyer buyer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(buyer.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerPhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BuyerPhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}

