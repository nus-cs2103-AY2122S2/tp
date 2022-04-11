package woofareyou.commons.util;

import java.util.Set;

import woofareyou.model.filter.DateContainsFilterDatePredicate;
import woofareyou.model.filter.FilterByContainsFilterWordPredicate;
import woofareyou.model.filter.OwnerNameContainsFilterWordPredicate;
import woofareyou.model.filter.TagContainsFilterWordPredicate;
import woofareyou.model.tag.Tag;

public class FilterUtil {

    /**
     * Returns true if the {@code petTags} contains the {@code filterWord}.
     * Ignores case, and a partial or full word match is required.
     * @param petTags cannot be null
     * @param filterWord cannot be null, cannot be empty
     */
    public static boolean tagContainFilterWord(Set<Tag> petTags, String filterWord) {
        CollectionUtil.requireAllNonNull(petTags, filterWord);

        String trimFilterWord = filterWord.trim();

        for (Tag tag : petTags) {
            String tagString = tag.toString();
            if (StringUtil.tagOrNameContainsWordsIgnoreCase(tagString, trimFilterWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if argument provided for filter is valid.
     * Parameter must only contain one field to be filtered by.
     * @param args Filter parameter
     * @return Returns true if argument provided is valid, else false
     */
    public static boolean isValidFilterArg(String args) {
        String[] separateFilterCommand = args.split("/");

        return separateFilterCommand.length <= 2;
    }

    /**
     * Matches predicate with its type to return a String message.
     * @param predicate Predicate that is to be matched.
     * @return String message to be used in filter success message.
     */
    public static String successMessageMatch(FilterByContainsFilterWordPredicate predicate) {
        if (predicate instanceof DateContainsFilterDatePredicate) {
            return "date";
        } else if (predicate instanceof TagContainsFilterWordPredicate) {
            return "breed";
        } else if (predicate instanceof OwnerNameContainsFilterWordPredicate) {
            return "owner's name";
        } else {
            return "appointment date";
        }
    }

}
