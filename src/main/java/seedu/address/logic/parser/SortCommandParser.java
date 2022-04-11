package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_SORT_DIRECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT_DIRECTION);

        String attribute;

        attribute = argMultimap.getPreamble();

        Set<String> validSortDirections = new HashSet<>();
        validSortDirections.add("asc");
        validSortDirections.add("desc");

        String sortDirection = argMultimap.getValue(PREFIX_SORT_DIRECTION).orElse("desc");

        if (!validSortDirections.contains(sortDirection)) {
            throw new ParseException(String.format(MESSAGE_INVALID_SORT_DIRECTION,
                    SortCommand.MESSAGE_INVALID_SORT_DIRECTION));
        }

        return new SortCommand(attribute, sortDirection.equals("asc"));
    }

}
