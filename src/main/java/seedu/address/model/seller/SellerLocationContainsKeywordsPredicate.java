package seedu.address.model.seller;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Seller}'s {@code Location} matches any of the keywords given.
 */
public class SellerLocationContainsKeywordsPredicate implements Predicate<Seller> {
    private final List<String> keywords;

    public SellerLocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Seller seller) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        seller.getPropertyToSell().getHouse().getLocation().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerLocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SellerLocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
