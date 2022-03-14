package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.AppointmentsBetweenCommand;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AppointmentsBetween object.
 */
public class AppointmentsBetweenCommandParser implements Parser<AppointmentsBetweenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentsBetweenCommand
     * and returns an AppointmentsBetweenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentsBetweenCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE_START, PREFIX_TIME_START,
                        PREFIX_DATE_END, PREFIX_TIME_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE_START, PREFIX_TIME_START, PREFIX_DATE_END,
                PREFIX_TIME_END) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentsBetweenCommand.MESSAGE_USAGE));
        }

        LocalDate startDate = argMultimap.getValue(PREFIX_DATE_START).flatMap(DateUtil::parseDate)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentsBetweenCommand.MESSAGE_START_DATE_INVALID)));
        LocalTime startTime = argMultimap.getValue(PREFIX_TIME_START).flatMap(DateUtil::parseTime)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentsBetweenCommand.MESSAGE_START_TIME_INVALID)));
        LocalDate endDate = argMultimap.getValue(PREFIX_DATE_END).flatMap(DateUtil::parseDate)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentsBetweenCommand.MESSAGE_END_DATE_INVALID)));
        LocalTime endTime = argMultimap.getValue(PREFIX_TIME_END).flatMap(DateUtil::parseTime)
                .orElseThrow(() -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AppointmentsBetweenCommand.MESSAGE_END_TIME_INVALID)));

        return new AppointmentsBetweenCommand(DateUtil.combineDateTime(startDate, startTime),
                DateUtil.combineDateTime(endDate, endTime));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
