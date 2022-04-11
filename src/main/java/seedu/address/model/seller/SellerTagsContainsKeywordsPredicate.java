package seedu.address.model.seller;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Seller}'s {@code Name} matches any of the keywords given.
 */
public class SellerTagsContainsKeywordsPredicate implements Predicate<Seller> {
    private final List<String> keywords;

    public SellerTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Seller seller) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(seller.getTags().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SellerTagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
