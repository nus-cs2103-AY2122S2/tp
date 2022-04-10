package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCompanyCommand;
import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {
    private final ListType listType;

    /**
     * Constructor for a ListCommandParser. Can create a new ListCommand
     * ased on the given {@code listType}.
     */
    public SortCommandParser(ListType listType) {
        requireNonNull(listType);
        this.listType = listType;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SEARCH_TYPE, PREFIX_ORDERING);
        String searchTypeString = argMultimap.getValue(PREFIX_SEARCH_TYPE).orElse("unarchived");
        String orderingString = argMultimap.getValue(PREFIX_ORDERING).orElse("ascending");

        try {
            SearchType searchType = ParserUtil.parseSearchType(searchTypeString);
            Ordering ordering = ParserUtil.parseOrdering(orderingString);

            switch (listType) {
            case PERSON:
                return new SortPersonCommand(searchType, ordering);
            case COMPANY:
                return new SortCompanyCommand(searchType, ordering);
            case EVENT:
                return new SortEventCommand(searchType, ordering);
            default:
                // Should never reach here
                return null;
            }
        } catch (ParseException pe) {
            String messageUsage = SortEventCommand.MESSAGE_USAGE;

            switch (listType) {
            case PERSON:
                messageUsage = SortPersonCommand.MESSAGE_USAGE;
                break;
            case COMPANY:
                messageUsage = SortCompanyCommand.MESSAGE_USAGE;
                break;
            case EVENT:
                messageUsage = SortEventCommand.MESSAGE_USAGE;
                break;
            default:
                // Should never reach here
            }

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage), pe);
        }
    }
}
