package seedu.address.logic.commands;

import java.util.function.Predicate;

import seedu.address.commons.core.SearchTypeUtil;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.entry.Entry;

public abstract class ListCommand extends Command {
    protected final SearchType searchType;

    public ListCommand(SearchType searchType) {
        this.searchType = searchType;
    }

    protected Predicate<Entry> getPredicate() {
        return SearchTypeUtil.getPredicate(searchType);
    }

    protected String getSuccessMessage() {
        switch (searchType) {
        case UNARCHIVED_ONLY:
            return " unarchived";
        case ARCHIVED_ONLY:
            return " archived";
        case ALL:
            return "";
        default:
            // Should not reach here
            throw new IllegalArgumentException("Inavlid searchType passed");
        }
    }
}
