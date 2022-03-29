package seedu.address.model.buyer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Buyer}'s {@code Location} matches any of the keywords given.
 */
public class BuyerLocationContainsKeywordsPredicate implements Predicate<Buyer> {
    private final List<String> keywords;

    public BuyerLocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Buyer buyer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        buyer.getPropertyToBuy().getHouse().getLocation().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BuyerLocationContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BuyerLocationContainsKeywordsPredicate) other).keywords)); // state check
    }

}
