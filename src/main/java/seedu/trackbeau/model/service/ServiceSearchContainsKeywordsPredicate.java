package seedu.trackbeau.model.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.trackbeau.commons.util.StringUtil;

/**
 * Tests that a {@code service's}'s {@code Data} matches any of the keywords given.
 */
public class ServiceSearchContainsKeywordsPredicate implements Predicate<Service> {
    public static final int FIND_ATTRIBUTE_COUNT = 3;
    private final ArrayList<List<String>> keywordsList;
    /**
     * Constructs a {@code Predicate}.
     *
     * @param keywordsList User input keywords to search with.
     */
    public ServiceSearchContainsKeywordsPredicate(ArrayList<List<String>> keywordsList) {
        this.keywordsList = keywordsList;
        assert keywordsList.size() == FIND_ATTRIBUTE_COUNT
                : String.format("Keyword list is wrong size: %s", keywordsList.toString());
    }

    @Override
    public boolean test(Service service) {
        String[] find = {"getName", "getPrice", "getDuration"};
        String searchString = "";
        Boolean anyMatch = false;

        try {
            for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
                List<String> keywords = keywordsList.get(i);
                searchString = service.getClass().getDeclaredMethod(find[i]).invoke(service).toString();

                if (keywords == null) {
                    continue;
                }

                for (String keyword : keywords) {
                    if (i == 1) {
                        Double temp = Double.parseDouble(keyword);
                        keyword = temp.toString();
                    }
                    if (StringUtil.containsWordIgnoreCase(searchString, keyword)) {
                        anyMatch = true;
                        break;
                    }
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return anyMatch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof ServiceSearchContainsKeywordsPredicate)) {
            return false;
        }

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            List<String> thisKeywords = this.keywordsList.get(i);
            List<String> otherKeywords = ((ServiceSearchContainsKeywordsPredicate) other).keywordsList.get(i);
            if ((thisKeywords != null) && (otherKeywords != null)) {
                if (!thisKeywords.equals(otherKeywords)) {
                    return false;
                }
            }
        }
        return true;
    }
}
