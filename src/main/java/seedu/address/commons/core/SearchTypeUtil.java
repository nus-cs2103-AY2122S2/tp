package seedu.address.commons.core;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.entry.Entry;

public class SearchTypeUtil {
    public static enum SearchType { UNARCHIVED_ONLY, ARCHIVED_ONLY, ALL }

    public static Predicate<Entry> getPredicate(SearchType searchType) {
        switch (searchType) {
        case UNARCHIVED_ONLY:
            return Model.PREDICATE_SHOW_UNARCHIVED_ONLY;
        case ARCHIVED_ONLY:
            return Model.PREDICATE_SHOW_ARCHIVED_ONLY;
        case ALL:
            return Model.PREDICATE_SHOW_ALL;
        default:
            // should never reach here
        }

        return null;
    }
}
