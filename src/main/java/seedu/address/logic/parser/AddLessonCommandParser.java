package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LESSON_NAME, PREFIX_SUBJECT, PREFIX_DATE, PREFIX_START_TIME,
                        PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES, PREFIX_RECURRING);

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON_NAME, PREFIX_SUBJECT, PREFIX_DATE, PREFIX_START_TIME,
                PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        String lessonName = ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get());
        String subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        String startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());

        int durationHours = ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get());
        int durationMinutes = ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get());

        Lesson lesson;
        if (isRecurring(argMultimap)) {
            // TODO: change this to instantiate a recurring lesson here
            lesson = Lesson.makeTemporaryLesson(lessonName, subject, getLessonDateTime(date, startTime), durationHours, durationMinutes);
        } else {
            lesson = Lesson.makeTemporaryLesson(lessonName, subject, getLessonDateTime(date, startTime), durationHours, durationMinutes);
        }

        return new AddLessonCommand(lesson);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if the lesson is recurring.
     */
    private static boolean isRecurring(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_RECURRING).isEmpty();
    }

    /**
     * Returns a {@code LocalDateTime} representing the date of the lesson and the time at which it starts.
     */
    private static LocalDateTime getLessonDateTime(LocalDate dateOfLesson, String startTime) throws ParseException {
        String[] hourAndMinuteOfStartTime = startTime.split(":");
        Integer hour;
        Integer minute;
        LocalDateTime lessonDatetime;

        try {
            hour = Integer.parseInt(hourAndMinuteOfStartTime[0]);
            minute = Integer.parseInt(hourAndMinuteOfStartTime[1]);
            lessonDatetime = dateOfLesson.atTime(hour, minute);
        } catch (NumberFormatException | DateTimeException exception) {
            throw new ParseException(String.format("Invalid lesson start time: %s", startTime));
        }

        return lessonDatetime;
    }

}
