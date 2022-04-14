package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<UnmarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand and returns a UnmarkCommand
     * object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public UnmarkCommand parse(String args) throws ParseException {
        UnmarkCommand unmarkCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied neither index nor id
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        } else if (arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no index
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no id
                && arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
        } else {
            try {
                StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
                Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
                unmarkCommand = new UnmarkCommand(studentId, index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE), pe);
            }
        }
        return unmarkCommand;
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
