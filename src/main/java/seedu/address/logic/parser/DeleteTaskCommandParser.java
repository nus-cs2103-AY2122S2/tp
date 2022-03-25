package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new DeleteTaskCommand object.
 */

public class DeleteTaskCommandParser implements Parser<DeleteTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTaskCommand and returns a
     * DeleteTaskCommand object for execution.
     *
     * @param args String to parse.
     * @return DeleteTaskCommand to execute.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public DeleteTaskCommand parse(String args) throws ParseException {
        DeleteTaskCommand deleteTaskCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied neither index nor id
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else if (arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no index
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no id
                && arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else {
            try {
                StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
                Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
                deleteTaskCommand = new DeleteTaskCommand(studentId, index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
            }
        }
        return deleteTaskCommand;
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
