package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDate;
import java.util.List;

import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_FRIEND_NAME);

        boolean isAnyArgumentGiven = argMultimap.arePrefixesPresent(PREFIX_NAME) ||
                argMultimap.arePrefixesPresent(PREFIX_DATE) ||
                argMultimap.arePrefixesPresent(PREFIX_FRIEND_NAME);

        if (!isAnyArgumentGiven || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        String eventNameSubstring;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            eventNameSubstring = argMultimap.getValue(PREFIX_NAME).get();
        } else {
            eventNameSubstring = "";
        }

        LocalDate searchDate;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            searchDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } else {
            searchDate = null;
        }

        List<String> friendNameSubstrings = argMultimap.getAllValues(PREFIX_FRIEND_NAME);

        return new FindEventCommand(eventNameSubstring, searchDate, friendNameSubstrings);
    }
}
