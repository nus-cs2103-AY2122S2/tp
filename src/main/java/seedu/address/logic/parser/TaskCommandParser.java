package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new TaskCommand object
 */
public class TaskCommandParser implements Parser<TaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TaskCommand
     * and returns a TaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public TaskCommand parse(String args) throws ParseException {
        TaskCommand taskCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (arePrefixesPresent(argMultimap, PREFIX_ID) || !argMultimap.getPreamble().isEmpty()) {
            StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
            taskCommand = new TaskCommand(studentId);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }
        return taskCommand;
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
