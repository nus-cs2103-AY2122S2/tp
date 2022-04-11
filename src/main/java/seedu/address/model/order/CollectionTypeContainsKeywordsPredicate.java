package seedu.address.model.order;

import java.util.function.Predicate;

/**
 * Tests that a {@code Order}'s {@code CollectionType} matches any of the keywords given.
 */
public class CollectionTypeContainsKeywordsPredicate implements Predicate<Order> {
    private final String keyword;

    public CollectionTypeContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    public String getKeywordsString() {
        return keyword;
    }

    @Override
    public boolean test(Order order) {
        return order.getCollectionType().getValue().equalsIgnoreCase(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CollectionTypeContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((CollectionTypeContainsKeywordsPredicate) other).keyword)); // state check
    }
}
