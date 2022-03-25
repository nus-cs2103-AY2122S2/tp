package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class FilterUtil {

    /**
     * Returns true if the {@code petTags} contains the {@code filterWord}.
     *   Ignores case, and a partial or full word match is required.
     * @param petTags cannot be null
     * @param filterWord cannot be null, cannot be empty
     */
    public static boolean tagContainFilterWord(Set<Tag> petTags, String filterWord) {
        requireNonNull(petTags);
        requireNonNull(filterWord);

        String trimFilterWord = filterWord.trim();

        for (Tag tag : petTags) {
            if (StringUtil.tagContainsWordsIgnoreCase(tag.toString(), trimFilterWord)) {
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
        String[] separateFilterCommand = args.split("\\s+");

        return separateFilterCommand.length <= 1;
    }

}
