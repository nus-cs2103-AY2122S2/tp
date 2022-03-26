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

        if (CheckPrefixes.arePrefixesAbsent(argMultimap, PREFIX_LESSON_NAME, PREFIX_SUBJECT, PREFIX_LESSON_ADDRESS,
                PREFIX_DATE, PREFIX_START_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        LessonName name = ParserUtil.parseLessonName(argMultimap.getValue(PREFIX_LESSON_NAME).get());
        Subject subject = ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get());
        LessonAddress address = ParserUtil.parseLessonAddress(argMultimap.getValue(PREFIX_LESSON_ADDRESS).get());
        Integer durationHours = hasDurationHoursField(argMultimap)
                ? ParserUtil.parseDurationHours(argMultimap.getValue(PREFIX_DURATION_HOURS).get())
                : 0;
        Integer durationMinutes = hasDurationMinutesField(argMultimap)
                ? ParserUtil.parseDurationMinutes(argMultimap.getValue(PREFIX_DURATION_MINUTES).get())
                : 0;

        ParserUtil.checkDurationIsValid(durationHours, durationMinutes);

        DateTimeSlot dateTimeSlot = ParserUtil.parseDateTimeSlot(
                argMultimap.getValue(PREFIX_DATE).get(),
                argMultimap.getValue(PREFIX_START_TIME).get(),
                durationHours,
                durationMinutes
        );

        Lesson lesson = isRecurring(argMultimap)
                ? Lesson.makeRecurringLesson(name, subject, address, dateTimeSlot)
                : Lesson.makeTemporaryLesson(name, subject, address, dateTimeSlot);

        return new AddLessonCommand(lesson);
    }

    /**
     * Returns true if the lesson is recurring.
     */
    private static boolean isRecurring(ArgumentMultimap argumentMultimap) {
        return !argumentMultimap.getValue(PREFIX_RECURRING).isEmpty();
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
