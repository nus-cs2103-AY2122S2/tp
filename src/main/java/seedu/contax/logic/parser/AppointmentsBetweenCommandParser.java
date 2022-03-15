package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.AppointmentsBetweenCommand;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AppointmentsBetweenCommand object.
 */
public class AppointmentsBetweenCommandParser implements Parser<AppointmentsBetweenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentsBetweenCommand
     * and returns an AppointmentsBetweenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AppointmentsBetweenCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE_START, PREFIX_TIME_START,
                        PREFIX_DATE_END, PREFIX_TIME_END);

        if (!argMultimap.arePrefixesPresent(PREFIX_DATE_START, PREFIX_TIME_START, PREFIX_DATE_END,
                PREFIX_TIME_END) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AppointmentsBetweenCommand.MESSAGE_USAGE));
        }

        LocalDate startDate = argMultimap.getValue(PREFIX_DATE_START).flatMap(DateUtil::parseDate)
                .orElseThrow(() -> new ParseException(AppointmentsBetweenCommand.MESSAGE_START_DATE_INVALID));
        LocalTime startTime = argMultimap.getValue(PREFIX_TIME_START).flatMap(DateUtil::parseTime)
                .orElseThrow(() -> new ParseException(AppointmentsBetweenCommand.MESSAGE_START_TIME_INVALID));
        LocalDate endDate = argMultimap.getValue(PREFIX_DATE_END).flatMap(DateUtil::parseDate)
                .orElseThrow(() -> new ParseException(AppointmentsBetweenCommand.MESSAGE_END_DATE_INVALID));
        LocalTime endTime = argMultimap.getValue(PREFIX_TIME_END).flatMap(DateUtil::parseTime)
                .orElseThrow(() -> new ParseException(AppointmentsBetweenCommand.MESSAGE_END_TIME_INVALID));

        LocalDateTime startDateTime = DateUtil.combineDateTime(startDate, startTime);
        LocalDateTime endDateTime = DateUtil.combineDateTime(endDate, endTime);

        if (endDateTime.isBefore(startDateTime)) {
            throw new ParseException(AppointmentsBetweenCommand.MESSAGE_END_BEFORE_START);
        }

        return new AppointmentsBetweenCommand(startDateTime, endDateTime);
    }
}
