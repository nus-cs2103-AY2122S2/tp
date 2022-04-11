package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Phone} matches any of the keywords given.
 */
public class OrderUuidContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderUuidContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getUuid().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderUuidContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((OrderUuidContainsKeywordsPredicate) other).keywords)); // state check
    }
}
