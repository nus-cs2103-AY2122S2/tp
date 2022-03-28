package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_ONLY;
import static seedu.address.model.Model.PREDICATE_SHOW_UNARCHIVED_ONLY;

import java.util.function.Predicate;

import seedu.address.logic.parser.ParserUtil.SearchType;
import seedu.address.model.entry.Entry;

public abstract class ListCommand extends Command {
    private final SearchType searchType;

    public ListCommand(SearchType searchType) {
        this.searchType = searchType;
    }

    protected Predicate<Entry> getPredicate() {
        switch (searchType) {
        case UNARCHIVED_ONLY:
            return PREDICATE_SHOW_UNARCHIVED_ONLY;
        case ARCHIVED_ONLY:
            return PREDICATE_SHOW_ARCHIVED_ONLY;
        case ALL:
            return PREDICATE_SHOW_ALL;
        default:
            // Should not reach here
            return null;
        }
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
            return null;
        }
    }
}
