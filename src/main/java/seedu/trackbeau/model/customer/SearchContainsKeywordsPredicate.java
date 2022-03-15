package seedu.trackbeau.model.customer;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.trackbeau.commons.util.StringUtil;
import seedu.trackbeau.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Data} matches any of the keywords given.
 */
public class SearchContainsKeywordsPredicate implements Predicate<Customer> {
    private final String searchArea;
    private final Integer searchType;
    private final List<String> keywords;

    /**
     * Constructs a {@code Predicate}.
     *
     * @param searchArea Data field of customer to search at.
     * @param searchType Search for tags if true or keywords if false.
     * @param keywords User input keywords to search with.
     */
    public SearchContainsKeywordsPredicate(String searchArea, Integer searchType, List<String> keywords) {
        this.searchArea = searchArea;
        this.searchType = searchType;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Customer customer) {
        String searchString = "";
        Boolean anyMatch = false;
        try {
            if (searchType == 0) {
                searchString = customer.getClass().getDeclaredMethod(searchArea).invoke(customer).toString();
            } else {
                // Will always return type Set<Tag> from the 3 possible methods in the Customer class.
                @SuppressWarnings("unchecked")
                Set<Tag> tagList = (Set<Tag>) customer.getClass().getDeclaredMethod(searchArea).invoke(customer);
                for (Tag tag : tagList) {
                    searchString = searchString + tag.tagName + " ";
                }
            }

            for (String keyword : keywords) {
                if (StringUtil.containsWordIgnoreCase(searchString, keyword)) {
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
