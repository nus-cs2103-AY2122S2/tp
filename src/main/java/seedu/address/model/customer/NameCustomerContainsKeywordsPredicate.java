package seedu.address.model.customer;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Customer}'s {@code NameCustomer} matches any of the keywords given.
 */
public class NameCustomerContainsKeywordsPredicate implements Predicate<Customer> {
    private final List<String> keywords;

    public NameCustomerContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(customer.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameCustomerContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameCustomerContainsKeywordsPredicate) other).keywords)); // state check
    }

}
