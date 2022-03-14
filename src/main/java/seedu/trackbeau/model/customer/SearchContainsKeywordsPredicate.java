package seedu.trackbeau.model.customer;

import java.util.List;
import java.util.function.Predicate;

import java.lang.reflect.*;

import seedu.trackbeau.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class SearchContainsKeywordsPredicate implements Predicate<Customer> {
    private final String searchArea;
    private final List<String> keywords;

    public SearchContainsKeywordsPredicate(String searchArea, List<String> keywords) {
        this.searchArea = searchArea;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        String method;
        Boolean anyMatch = false;
        try {
            method = customer.getClass().getDeclaredMethod(searchArea).invoke(customer).toString();
            for (String keyword : keywords) {
                if (StringUtil.containsWordIgnoreCase(method, keyword)) {
                    anyMatch = true;
                    break;
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return anyMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SearchContainsKeywordsPredicate) other).keywords)); // state check
    }

}
