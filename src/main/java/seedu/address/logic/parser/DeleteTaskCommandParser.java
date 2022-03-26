package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;

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
    public DeleteTaskCommand parse(String args) throws ParseException {
        DeleteTaskCommand deleteTaskCommand;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_INDEX, PREFIX_MODULE_CODE, PREFIX_TASK_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied neither index nor id nor module code nor task name
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)
                && !arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE)
                && !arePrefixesPresent(argMultimap, PREFIX_TASK_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else if (arePrefixesPresent(argMultimap, PREFIX_ID) // supplied either index or id
                || arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            deleteTaskCommand = indexOrStudentIdGiven(argMultimap);
        } else {
            deleteTaskCommand = moduleCodeOrTaskNameGiven(argMultimap);
        }
        return deleteTaskCommand;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    private static DeleteTaskCommand indexOrStudentIdGiven(ArgumentMultimap argMultimap) throws ParseException {

        if (arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no index
                && !arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_ID) // supplied no id
                && arePrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else {
            try {
                StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
                Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
                return new DeleteTaskCommand(studentId, index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    private static DeleteTaskCommand moduleCodeOrTaskNameGiven(ArgumentMultimap argMultimap) throws ParseException {

        if (arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE) // supplied no task name
                && !arePrefixesPresent(argMultimap, PREFIX_TASK_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_CODE) // supplied no module code
                && arePrefixesPresent(argMultimap, PREFIX_TASK_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE));
        } else {
            try {
                ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());
                Task task = ParserUtil.parseTask(argMultimap.getValue(PREFIX_TASK_NAME).get());
                return new DeleteTaskCommand(moduleCode, task);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
