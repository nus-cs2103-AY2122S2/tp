package seedu.address.logic.commands;

import seedu.address.logic.parser.ParserUtil.SearchType;

public abstract class ListCommand extends Command {
    private final SearchType searchType;

    public ListCommand(SearchType searchType) {
        this.searchType = searchType;
    }
}
