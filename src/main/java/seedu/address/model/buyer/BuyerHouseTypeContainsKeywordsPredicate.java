package seedu.address.model.buyer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Buyer}'s {@code Housetype} matches any of the keywords given.
 */
public class BuyerHouseTypeContainsKeywordsPredicate implements Predicate<Buyer> {
    private final List<String> keywords;

    public BuyerHouseTypeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Buyer buyer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        buyer.getPropertyToBuy().getHouse().getHouseType().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerHouseTypeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BuyerHouseTypeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
