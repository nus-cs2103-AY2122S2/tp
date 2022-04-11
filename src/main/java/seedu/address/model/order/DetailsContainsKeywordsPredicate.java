package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Order}'s {@code Details} matches any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    private boolean testKeywordInDetails(Details details) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(details.item, keyword));
    }

    public String getKeywordsString() {
        return keywords.toString();
    }

    @Override
    public boolean test(Order order) {
        return order.getDetails().stream()
                .anyMatch(details -> testKeywordInDetails(details));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
