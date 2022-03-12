package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import java.util.Optional;
import java.util.stream.Stream;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;


/**
 * Parses input arguments and creates a new AssignCommand object.
 */
public class AssignCommandParser implements Parser<AssignCommand> {

    public AssignCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_TASK_NAME);

        // Check if fields are present in the user input.
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_TASK_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        Optional<String> currStudentId = argMultimap.getValue(PREFIX_ID);
        StudentId studentId;
        if (currStudentId.isEmpty()) {
            studentId = null;
        } else {
            studentId = ParserUtil.parseStudentId(currStudentId.get());
        }

        Optional<String> currTask = argMultimap.getValue(PREFIX_TASK_NAME);
        Task task;
        if (currTask.isEmpty()) {
            task = null;
        } else {
            task = ParserUtil.parseTask(currTask.get());
        }

        return new AssignCommand(studentId, task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
