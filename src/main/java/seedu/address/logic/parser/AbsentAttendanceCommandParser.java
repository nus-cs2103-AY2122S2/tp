package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AbsentAttendanceCommand;
import seedu.address.logic.commands.AbsentAttendanceCommand.AbsentAttendanceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AbsentAttendanceCommand object.
 */
public class AbsentAttendanceCommandParser implements Parser<AbsentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbsentAttendanceCommand
     * and returns a AbsentAttendanceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AbsentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AbsentAttendanceCommand.MESSAGE_USAGE), pe);
        }

        AbsentAttendanceDescriptor absentAttendanceDescriptor =
                new AbsentAttendanceDescriptor();

        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AbsentAttendanceCommand.MESSAGE_USAGE));
        }

        absentAttendanceDescriptor.setAttendanceDate(
                ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_DATE).get()));

        return new AbsentAttendanceCommand(index, absentAttendanceDescriptor);
    }
}
