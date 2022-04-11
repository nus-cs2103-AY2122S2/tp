package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class OrderStatusContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderStatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(order.getStatus().name(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderStatusContainsKeywordsPredicate
                // instanceof handles nulls
                && keywords.equals(((OrderStatusContainsKeywordsPredicate) other).keywords)); // state check
    }
}

