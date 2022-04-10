package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListCompanyCommand;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    private final ListType listType;

    /**
     * Constructor for a ListCommandParser. Can create a new ListCommand
     * ased on the given {@code listType}.
     */
    public ListCommandParser(ListType listType) {
        requireNonNull(listType);
        this.listType = listType;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SEARCH_TYPE);
        String searchTypeString = argMultimap.getValue(PREFIX_SEARCH_TYPE).orElse("unarchived");

        try {
            SearchType searchType = ParserUtil.parseSearchType(searchTypeString);

            switch (listType) {
            case PERSON:
                return new ListPersonCommand(searchType);
            case COMPANY:
                return new ListCompanyCommand(searchType);
            case EVENT:
                return new ListEventCommand(searchType);
            default:
                // Should never reach here
                return null;
            }
        } catch (ParseException pe) {
            String messageUsage = ListEventCommand.MESSAGE_USAGE;

            switch (listType) {
            case PERSON:
                messageUsage = ListPersonCommand.MESSAGE_USAGE;
                break;
            case COMPANY:
                messageUsage = ListCompanyCommand.MESSAGE_USAGE;
                break;
            case EVENT:
                messageUsage = ListEventCommand.MESSAGE_USAGE;
                break;
            default:
                // Should never reach here
            }

            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage), pe);
        }
    }
}
