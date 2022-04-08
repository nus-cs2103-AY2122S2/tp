package woofareyou.logic.parser;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.commons.core.Messages.MESSAGE_MISSING_DROPOFF_TIME;
import static woofareyou.commons.core.Messages.MESSAGE_MISSING_PICKUP_TIME;
import static woofareyou.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DATE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DROPOFF;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PICKUP;

import java.time.LocalDate;
import java.util.stream.Stream;

import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.PresentAttendanceCommand;
import woofareyou.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PresentAttendanceCommand object.
 */
public class PresentAttendanceCommandParser implements Parser<PresentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PresentAttendanceCommand
     * and returns a PresentAttendanceCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PresentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_PICKUP, PREFIX_DROPOFF);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PresentAttendanceCommand.MESSAGE_USAGE));
        }

        checkIfOnlyOneTimePresent(argMultimap);

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        LocalDate attendanceDate;
        PresentAttendanceDescriptor presentAttendanceDescriptor =
            new PresentAttendanceDescriptor();
        attendanceDate = ParserUtil.parseAttendanceDate(argMultimap.getValue(PREFIX_DATE).get());
        presentAttendanceDescriptor.setAttendanceDate(attendanceDate);

        if (argMultimap.getValue(PREFIX_PICKUP).isPresent()) {
            presentAttendanceDescriptor.setPickUpTime(
                ParserUtil.parsePickUpTime(argMultimap.getValue(PREFIX_PICKUP).get())
            );
        }

        if (argMultimap.getValue(PREFIX_DROPOFF).isPresent()) {
            presentAttendanceDescriptor.setDropOffTime(
                ParserUtil.parseDropOffTime(argMultimap.getValue(PREFIX_DROPOFF).get())
            );
        }

        return new PresentAttendanceCommand(index, presentAttendanceDescriptor);
    }

    /**
     * Checks to see if only pick up or only drop off time for the command is present.
     *
     * @param argumentMultimap the arguments of the command.
     * @throws ParseException if one and only one out of the two timings are provided.
     */
    private void checkIfOnlyOneTimePresent(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getValue(PREFIX_PICKUP).isEmpty()
            && argumentMultimap.getValue(PREFIX_DROPOFF).isPresent()) {
            throw new ParseException(MESSAGE_MISSING_PICKUP_TIME);
        } else if (argumentMultimap.getValue(PREFIX_PICKUP).isPresent()
            && argumentMultimap.getValue(PREFIX_DROPOFF).isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_DROPOFF_TIME);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

