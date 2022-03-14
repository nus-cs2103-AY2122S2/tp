package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {
    public static final String INVALID_DURATION_MESSAGE = "Duration of lesson cannot be zero.";

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LESSON_NAME, PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS,
                        PREFIX_DATE, PREFIX_START_TIME, PREFIX_DURATION_HOURS,
                        PREFIX_DURATION_MINUTES, PREFIX_RECURRING);

        if (CheckPrefixes.arePrefixesAbsent(argMultimap, PREFIX_LESSON_NAME, PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS,
                PREFIX_DATE, PREFIX_START_TIME, PREFIX_DURATION_HOURS, PREFIX_DURATION_MINUTES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        String lessonName = ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get());
        String subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        String startTime = ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_START_TIME).get());
        String address = ParserUtil.parseStartLessonAddress(argMultimap.getValue(PREFIX_LESSON_ADDRESS).get());

        int durationHours = ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get());
        int durationMinutes = ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get());
        checkDurationIsValid(durationHours, durationMinutes);

        Lesson lesson;
        if (isRecurring(argMultimap)) {
            lesson = Lesson.makeRecurringLesson(lessonName, subject, address, date,
                    startTime , durationHours, durationMinutes);
        } else {
            lesson = Lesson.makeTemporaryLesson(lessonName, subject, address, date,
                    startTime, durationHours, durationMinutes);
        }

        return new AddLessonCommand(lesson);
    }

    /**
     * Returns true if the lesson is recurring.
     */
    private static boolean isRecurring(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_RECURRING).isEmpty();
    }

    /**
     * Checks that the lesson has does not have a total duration of zero minutes.
     */
    private static void checkDurationIsValid(int hours, int minutes) throws ParseException {
        boolean isValidHoursAndMinutes = ((hours > 0 && minutes >= 0 && minutes <= 60)
                || (hours == 0 && minutes > 0 && minutes <= 60));

        if (!isValidHoursAndMinutes) {
            throw new ParseException(INVALID_DURATION_MESSAGE);
        }
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
