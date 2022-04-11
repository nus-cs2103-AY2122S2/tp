package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Phone} matches any of the keywords given.
 */
public class OrderContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getCustomerPhone(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrderContainsKeywordsPredicate)
                && keywords.equals(((OrderContainsKeywordsPredicate) other).keywords);
    }
}
