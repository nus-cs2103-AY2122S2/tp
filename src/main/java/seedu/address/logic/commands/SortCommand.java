package seedu.address.logic.commands;

import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;

public abstract class SortCommand extends ListCommand {
    protected final Ordering ordering;

    /**
     * Constructs a command with the given {@code ordering}.
     * @param searchType
     * @param ordering
     */
    public SortCommand(SearchType searchType, Ordering ordering) {
        super(searchType);
        this.ordering = ordering;
    }

    protected Ordering getOrdering() {
        return ordering;
    }

    @Override
    protected String getSuccessMessage() {
        switch (ordering) {
        case ASCENDING:
            return super.getSuccessMessage() + " in ascending order";
        case DESCENDING:
            return super.getSuccessMessage() + " in descending order";
        default:
            // Should not reach here
            return null;
        }
    }
}
