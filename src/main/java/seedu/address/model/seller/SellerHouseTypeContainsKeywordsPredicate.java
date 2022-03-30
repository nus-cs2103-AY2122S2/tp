package seedu.address.model.seller;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Seller}'s {@code Housetype} matches any of the keywords given.
 */
public class SellerHouseTypeContainsKeywordsPredicate implements Predicate<Seller> {
    private final List<String> keywords;

    public SellerHouseTypeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Seller seller) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        seller.getPropertyToSell().getHouse().getHouseType().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerHouseTypeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SellerHouseTypeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
