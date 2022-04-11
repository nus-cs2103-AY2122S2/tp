package seedu.trackbeau.model.customer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.trackbeau.model.tag.Tag;

/**
 * Tests that a {@code Customer's}'s {@code Data} matches any of the keywords given.
 */
public class CustomerSearchContainsKeywordsPredicate implements Predicate<Customer> {
    public static final int FIND_ATTRIBUTE_COUNT = 11;
    public static final int NON_TAG_ATTRIBUTE_COUNT = 8;
    private final ArrayList<List<String>> keywordsList;
    /**
     * Constructs a {@code Predicate}.
     *
     * @param keywordsList User input keywords to search with.
     */
    public CustomerSearchContainsKeywordsPredicate(ArrayList<List<String>> keywordsList) {
        this.keywordsList = keywordsList;
        assert keywordsList.size() == FIND_ATTRIBUTE_COUNT
                : String.format("Keyword list is wrong size: %s", keywordsList.toString());
    }

    @Override
    public boolean test(Customer customer) {
        String[] find = {"getName", "getPhone", "getEmail", "getAddress", "getSkinType",
            "getHairType", "getBirthdate", "getRegDate", "getStaffs", "getServices", "getAllergies"};

        try {
            for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
                //keywords contains the information that user wants to find for a particular attribute
                List<String> keywords = keywordsList.get(i);

                //customer is not searching about this attribute
                if (keywords == null) {
                    continue;
                }

                String searchString = ""; //searchString contains the existing customer information
                if (i < NON_TAG_ATTRIBUTE_COUNT) {
                    //get customer detail
                    searchString = customer.getClass().getDeclaredMethod(find[i]).invoke(customer).toString();
                } else {
                    Set<Tag> tagList = (Set<Tag>) customer.getClass().getDeclaredMethod(find[i]).invoke(customer);
                    for (Tag tag : tagList) {
                        searchString = searchString + tag.tagName + " ";
                    }
                }

                //loop through the keywords individually to check if match with customer information
                searchString = searchString.toLowerCase();
                for (String keyword : keywords) {
                    keyword = keyword.toLowerCase();
                    if (searchString.contains(keyword)) {
                        return true;
                    }
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof CustomerSearchContainsKeywordsPredicate)) {
            return false;
        }

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            List<String> thisKeywords = this.keywordsList.get(i);
            List<String> otherKeywords = ((CustomerSearchContainsKeywordsPredicate) other).keywordsList.get(i);
            if ((thisKeywords != null) && (otherKeywords != null)) {
                if (!thisKeywords.equals(otherKeywords)) {
                    return false;
                }
            }
        }
        return true;
    }

}
