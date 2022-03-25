package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.swing.text.html.Option;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.DateTimeSlot;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonAddress;
import seedu.address.model.lesson.LessonName;
import seedu.address.model.lesson.Subject;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String INVALID_DATE_FORMAT_MESSAGE = "Invalid date format! Date must be in DD-MM-YYYY\n"
            + "[EXAMPLE]: to specify that a lesson is on 25th March 2022, include the following\n"
            + "-d 25-03-2022";

    public static final String INVALID_DURATION_MESSAGE = "Duration of lesson cannot be zero.";

    public static final String INVALID_HOURS_FORMAT_MESSAGE = "Invalid duration in hours format! "
            + "Hours must be a non-negative integer.\n"
            + "[EXAMPLE] to specify that the hours field in the lesson's duration is 2 hours, "
            + "include the following\n"
            + "-h 2";
    public static final String NEGATIVE_HOURS_MESSAGE = "Hours cannot be lesser than 0.";

    public static final String INVALID_START_TIME_MESSAGE = "Invalid start time format! Start time must be in HH:mm\n"
            + "[EXAMPLE] to specify that a lesson starts at 6:30PM, include the following\n"
            + "-t 18:30";

    public static final String INVALID_MINUTES_FORMAT_MESSAGE = "Invalid duration in minutes format! "
            + "Minutes must be a non-negative integer.\n"
            + "[EXAMPLE] to specify that the minutes field in the lesson's duration is 25 minutes, "
            + "include the following\n"
            + "-m 25";
    public static final String NEGATIVE_MINUTES_MESSAGE = "Minutes cannot be lesser than 0.";
    public static final String MINUTES_GREATER_THAN_59_MESSAGE = "Minutes cannot be greater than 59.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code LessonName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String lessonName} into a {@code LessonName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lessonName} is invalid.
     */
    public static LessonName parseLessonName(String lessonName) throws ParseException {
        requireNonNull(lessonName);
        String trimmedName = lessonName.trim();
        if (!LessonName.isValidName(trimmedName)) {
            throw new ParseException(LessonName.MESSAGE_CONSTRAINTS);
        }
        return new LessonName(trimmedName);
    }

    /**
     * Parses a {@code String subject} into a {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses a {@code String address} into a {@code LessonAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static LessonAddress parseLessonAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!LessonAddress.isValidAddress(trimmedAddress)) {
            throw new ParseException(LessonAddress.MESSAGE_CONSTRAINTS);
        }

        return new LessonAddress(trimmedAddress);
    }

    /**
     * Checks that the lesson has does not have a total duration of zero minutes.
     */
    public static void checkDurationIsValid(int hours, int minutes) throws ParseException {
        boolean isValidHoursAndMinutes = ((hours > 0 && minutes >= 0 && minutes <= 60)
                || (hours == 0 && minutes > 0 && minutes <= 60));

        if (!isValidHoursAndMinutes) {
            throw new ParseException(INVALID_DURATION_MESSAGE);
        }
    }

    /**
     * Parses a {@code String dateOfLesson}, a {@code startTime}, a {@code duration hour-field}
     * and a {@code duration minute-field} into a {@code DateTimeSlot}
     *
     * @throws ParseException if the given {@code dateOfLesson} is invalid.
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static DateTimeSlot parseDateTimeSlot(String dateOfLesson, String startTime,
                                                 int durationHours, int durationMinutes) throws ParseException {
        LocalDate lessonDate = parseDate(dateOfLesson);
        LocalTime lessonStartTime = parseStartTime(startTime);

        return new DateTimeSlot(lessonDate.atTime(lessonStartTime), durationHours, durationMinutes);
    }

    /**
     * Parses a {@code String dateOfLesson} into a {@code LocalDate}.
     *
     * @throws ParseException if the given {@code dateOfLesson} is invalid.
     */
    public static LocalDate parseDate(String dateOfLesson) throws ParseException {
        requireNonNull(dateOfLesson);
        String trimmedDateString = dateOfLesson.trim();

        if (!DateTimeSlot.isValidDate(dateOfLesson)) {
            throw new ParseException(INVALID_DATE_FORMAT_MESSAGE);
        }

        LocalDate lessonDate;
        try {
            DateTimeFormatter acceptedDateTimeFormat = DateTimeSlot.getAcceptedDateFormat();
            lessonDate = LocalDate.parse(trimmedDateString, acceptedDateTimeFormat);
        } catch (DateTimeParseException exception) {
            throw new ParseException(INVALID_DATE_FORMAT_MESSAGE);
        }

        return lessonDate;
    }

    /**
     * Parses a {@code String startTime} into a {@code String}.
     *
     * @throws ParseException if the given {@code dateOfLesson} is invalid.
     */
    public static LocalTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTimeString = startTime.trim();
        if (!DateTimeSlot.isValidStartTime(trimmedStartTimeString)) {
            throw new ParseException(INVALID_START_TIME_MESSAGE);
        }
        String[] hourAndMinuteOfStartTime = trimmedStartTimeString.split(":");
        try {
            return LocalTime.of(Integer.parseInt(hourAndMinuteOfStartTime[0]),
                    Integer.parseInt(hourAndMinuteOfStartTime[1]));
        } catch (NumberFormatException | DateTimeException exception) {
            throw new ParseException(String.format("Invalid lesson start time: %s", startTime));
        }

    }

    /**
     * Parses a {@code String durationHours} into an {@code Integer}.
     *
     * @throws ParseException if the given {@code lessonDurationHours} is invalid.
     */
    public static int parseDurationHours(String durationHours) throws ParseException {
        requireNonNull(durationHours);
        String trimmedDurationString = durationHours.trim();

        Integer hours;
        try {
            hours = Integer.parseInt(trimmedDurationString);
        } catch (NumberFormatException exception) {
            throw new ParseException(INVALID_HOURS_FORMAT_MESSAGE);
        }

        if (hours < 0) {
            throw new ParseException(NEGATIVE_HOURS_MESSAGE);
        }

        return hours;
    }

    /**
     * Parses a {@code String durationMinutes} into an {@code Integer}.
     *
     * @throws ParseException if the given {@code lessonDurationMinutes} is invalid.
     */
    public static int parseDurationMinutes(String durationMinutes) throws ParseException {
        requireNonNull(durationMinutes);
        String trimmedDurationString = durationMinutes.trim();

        Integer minutes;
        try {
            minutes = Integer.parseInt(trimmedDurationString);
        } catch (NumberFormatException exception) {
            throw new ParseException(INVALID_MINUTES_FORMAT_MESSAGE);
        }

        if (minutes < 0) {
            throw new ParseException(NEGATIVE_MINUTES_MESSAGE);
        }

        if (minutes > 59) {
            throw new ParseException(MINUTES_GREATER_THAN_59_MESSAGE);
        }

        return minutes;
    }
}
