package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.function.Function;

import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.FreeBetweenCommand;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FreeBetweenCommand object.
 */
public class FreeBetweenCommandParser implements Parser<FreeBetweenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FreeBetweenCommand
     * and returns an FreeBetweenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FreeBetweenCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE_START, PREFIX_TIME_START,
                        PREFIX_DATE_END, PREFIX_TIME_END, PREFIX_DURATION);

        if (argMultimap.getValue(PREFIX_DURATION).isEmpty()
                || (argMultimap.getValue(PREFIX_TIME_END).isPresent()
                        && argMultimap.getValue(PREFIX_DATE_END).isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FreeBetweenCommand.MESSAGE_USAGE));
        }

        int duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()).value;
        LocalDate startDate = getArgumentOrElse(LocalDate.now(), argMultimap.getValue(PREFIX_DATE_START),
                DateUtil::parseDate, FreeBetweenCommand.MESSAGE_START_DATE_INVALID);
        LocalTime startTime = getArgumentOrElse(LocalTime.of(0, 0), argMultimap.getValue(PREFIX_TIME_START),
                DateUtil::parseTime, FreeBetweenCommand.MESSAGE_START_TIME_INVALID);
        LocalDate endDate = getArgumentOrElse(LocalDate.MAX, argMultimap.getValue(PREFIX_DATE_END),
                DateUtil::parseDate, FreeBetweenCommand.MESSAGE_END_DATE_INVALID);
        LocalTime endTime = getArgumentOrElse(LocalTime.of(23, 59), argMultimap.getValue(PREFIX_TIME_END),
                DateUtil::parseTime, FreeBetweenCommand.MESSAGE_END_TIME_INVALID);

        LocalDateTime startDateTime = DateUtil.combineDateTime(startDate, startTime);
        LocalDateTime endDateTime = DateUtil.combineDateTime(endDate, endTime);

        if (endDateTime.isBefore(startDateTime)) {
            throw new ParseException(FreeBetweenCommand.MESSAGE_END_BEFORE_START);
        }

        return new FreeBetweenCommand(startDateTime, endDateTime, duration);
    }

    /**
     * Parses a String argument into an object using the supplied {@code parserFunction}.
     * Returns the supplied default value if the String argument is empty.
     *
     * @param defaultValue The default value to use if the argument was not supplied.
     * @param argument An optional containing a String argument to parse, if supplied.
     * @param parserFunction The function for parsing the supplied String argument into an object.
     * @param parseErrorMessage The error message to throw if {@code parserFunction} fails.
     * @param <T> The parsed type of the argument.
     * @return An object of type {@code T} if parsing is successful.
     * @throws ParseException If an error occurs during parsing.
     */
    private <T> T getArgumentOrElse(T defaultValue,
                                    Optional<String> argument,
                                    Function<String, Optional<T>> parserFunction,
                                    String parseErrorMessage) throws ParseException {
        if (argument.isEmpty()) {
            return defaultValue;
        }

        return argument.flatMap(parserFunction)
                .orElseThrow(() -> new ParseException(parseErrorMessage));
    }
}
