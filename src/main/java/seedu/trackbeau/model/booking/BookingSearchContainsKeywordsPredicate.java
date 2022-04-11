package seedu.trackbeau.model.booking;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.trackbeau.commons.util.StringUtil;

/**
 * Tests that a {@code booking's}'s {@code Data} matches any of the keywords given.
 */
public class BookingSearchContainsKeywordsPredicate implements Predicate<Booking> {
    public static final int FIND_ATTRIBUTE_COUNT = 3;
    private final ArrayList<List<String>> keywordsList;
    /**
     * Constructs a {@code Predicate}.
     *
     * @param keywordsList User input keywords to search with.
     */
    public BookingSearchContainsKeywordsPredicate(ArrayList<List<String>> keywordsList) {
        this.keywordsList = keywordsList;
        assert keywordsList.size() == FIND_ATTRIBUTE_COUNT
                : String.format("Keyword list is wrong size: %s", keywordsList.toString());
    }

    @Override
    public boolean test(Booking booking) {
        String[] find = {"getCustomerName", "getServiceName", "getBookingDateTime", "getFeedback"};
        String searchString = "";

        try {
            List<String> keywords = keywordsList.get(0);
            for (int i = 0; i < 2; i++) {
                searchString = booking.getClass().getDeclaredMethod(find[i]).invoke(booking).toString();

                if (keywords == null) {
                    continue;
                }

                for (String keyword : keywords) {
                    if (StringUtil.containsWordIgnoreCase(searchString, keyword)) {
                        return true;
                    }
                }
            }

            for (int i = 1; i < FIND_ATTRIBUTE_COUNT; i++) {
                keywords = keywordsList.get(i);
                searchString = booking.getClass().getDeclaredMethod(find[i + 1]).invoke(booking).toString();

                if (keywords == null) {
                    continue;
                }

                for (String keyword : keywords) {
                    if (StringUtil.containsWordIgnoreCase(searchString, keyword)) {
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
        } else if (!(other instanceof BookingSearchContainsKeywordsPredicate)) {
            return false;
        }

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            List<String> thisKeywords = this.keywordsList.get(i);
            List<String> otherKeywords = ((BookingSearchContainsKeywordsPredicate) other).keywordsList.get(i);
            if ((thisKeywords != null) && (otherKeywords != null)) {
                if (!thisKeywords.equals(otherKeywords)) {
                    return false;
                }
            }
        }
        return true;
    }
}
