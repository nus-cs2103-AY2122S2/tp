package seedu.address.model.seller;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Seller}'s {@code Phone} matches any of the keywords given.
 */
public class SellerPhoneContainsKeywordsPredicate implements Predicate<Seller> {
    private final List<String> keywords;

    public SellerPhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Seller seller) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(seller.getPhone().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerPhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SellerPhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}

