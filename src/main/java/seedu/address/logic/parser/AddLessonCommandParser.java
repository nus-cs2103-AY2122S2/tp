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

import seedu.address.logic.commands.AddLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

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

        if (CheckPrefixes.arePrefixesAbsent(argMultimap, PREFIX_LESSON_NAME, PREFIX_DATE, PREFIX_START_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        LessonName name = getName(argMultimap);
        Subject subject = getSubject(argMultimap);
        LessonAddress address = getAddress(argMultimap);
        DateTimeSlot dateTimeSlot;
        dateTimeSlot = getDateTimeSlot(argMultimap);

        Lesson lesson = isRecurring(argMultimap)
                ? Lesson.makeRecurringLesson(name, subject, address, dateTimeSlot)
                : Lesson.makeTemporaryLesson(name, subject, address, dateTimeSlot);

        return new AddLessonCommand(lesson);
    }

    /**
     * Returns an instance of LessonName.
     */
    private static LessonName getName(ArgumentMultimap argumentMultimap) throws ParseException {
        return ParserUtil.parseLessonName(argumentMultimap.getValue(PREFIX_LESSON_NAME).get());
    }

    /**
     * Returns an instance of Subject.
     */
    private static Subject getSubject(ArgumentMultimap argumentMultimap) throws ParseException {
        if (hasSubjectField(argumentMultimap)) {
            return ParserUtil.parseSubject(argumentMultimap.getValue(PREFIX_SUBJECT).get());
        }

        return Subject.EMPTY_SUBJECT;
    }

    /**
     * Returns an instance of LessonAddress.
     */
    private static LessonAddress getAddress(ArgumentMultimap argumentMultimap) throws ParseException {
        if (hasAddressField(argumentMultimap)) {
            return ParserUtil.parseLessonAddress(argumentMultimap.getValue(PREFIX_LESSON_ADDRESS).get());
        }

        return LessonAddress.EMPTY_ADDRESS;
    }

    /**
     * Returns an integer representing the hour field of the lesson's duration
     */
    private static Integer getDurationHours(ArgumentMultimap argumentMultimap) throws ParseException {
        if (hasDurationHoursField(argumentMultimap)) {
            return ParserUtil.parseDurationHours(argumentMultimap.getValue(PREFIX_DURATION_HOURS).get());
        }

        return 0;
    }

    /**
     * Returns an integer representing the minute field of the lesson's duration
     */
    private static Integer getDurationMinutes(ArgumentMultimap argumentMultimap) throws ParseException {
        if (hasDurationMinutesField(argumentMultimap)) {
            return ParserUtil.parseDurationMinutes(argumentMultimap.getValue(PREFIX_DURATION_MINUTES).get());
        }

        return 0;
    }

    /**
     * Returns an instance of DateTimeSlot.
     */
    private static DateTimeSlot getDateTimeSlot(ArgumentMultimap argumentMultimap)
            throws ParseException {
        String date = getDate(argumentMultimap);
        String startTime = getStartTime(argumentMultimap);
        Integer durationHours = getDurationHours(argumentMultimap);
        Integer durationMinutes = getDurationMinutes(argumentMultimap);

        return ParserUtil.parseDateTimeSlot(
                date,
                startTime,
                durationHours,
                durationMinutes
        );
    }

    /**
     * Returns a String representing a lesson's date.
     */
    private static String getDate(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_DATE).get();
    }

    /**
     * Returns a String representing a lesson's starting time.
     */
    private static String getStartTime(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_START_TIME).get();
    }

    /**
     * Returns true if the lesson is recurring.
     */
    private static boolean isRecurring(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_RECURRING).isEmpty();
    }

    /**
     * Returns true if subject is specified
     */
    private static boolean hasSubjectField(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_SUBJECT).isEmpty();
    }

    /**
     * Returns true if address is specified
     */
    private static boolean hasAddressField(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_LESSON_ADDRESS).isEmpty();
    }

    /**
     * Returns true if hour field is specified for lesson duration
     */
    private static boolean hasDurationHoursField(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_DURATION_HOURS).isEmpty();
    }

    /**
     * Returns true if minutes field is specified for lesson duration
     */
    private static boolean hasDurationMinutesField(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_DURATION_MINUTES).isEmpty();
    }


}
