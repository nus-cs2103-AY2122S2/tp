package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.contact.Telegram;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String name} into a {@code Name}.
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
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> namesSet = new HashSet<>();
        for (String name : names) {
            namesSet.add(parseName(name));
        }
        return namesSet;
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
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidId(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
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
     * Parses a {@code String meetingName} into a {@code MeetingName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingName} is invalid.
     */
    public static MeetingName parseMeetingName(String meetingName) throws ParseException {
        requireNonNull(meetingName);
        String trimmedMeetingName = meetingName.trim();
        if (!MeetingName.isValidMeetingName(trimmedMeetingName)) {
            throw new ParseException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        return new MeetingName(trimmedMeetingName);
    }

    /**
     * Parses {@code Collection<String> meetingNames} into a {@code Set<MeetingName>}.
     */
    public static Set<MeetingName> parseMeetingNames(Collection<String> meetingNames) throws ParseException {
        requireNonNull(meetingNames);
        final Set<MeetingName> meetingNamesSet = new HashSet<>();
        for (String meetingName : meetingNames) {
            meetingNamesSet.add(parseMeetingName(meetingName));
        }
        return meetingNamesSet;
    }

    /**
     * Parses a {@code String meetingDate} into a {@code MeetingDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingDate} is invalid.
     */
    public static MeetingDate parseMeetingDate(String meetingDate) throws ParseException {
        requireNonNull(meetingDate);
        String trimmedMeetingDate = meetingDate.trim();
        if (!MeetingDate.isValidDate(trimmedMeetingDate)) {
            throw new ParseException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
        return new MeetingDate(trimmedMeetingDate);
    }

    /**
     * Parses {@code Collection<String> meetingDates} into a {@code Set<MeetingDate>}.
     */
    public static Set<MeetingDate> parseMeetingDates(Collection<String> meetingDates) throws ParseException {
        requireNonNull(meetingDates);
        final Set<MeetingDate> meetingDatesSet = new HashSet<>();
        for (String meetingDate : meetingDates) {
            meetingDatesSet.add(parseMeetingDate(meetingDate));
        }
        return meetingDatesSet;
    }

    /**
     * Parses a {@code String startTime} into a {@code StartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static StartTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!StartTime.isValidTime(trimmedStartTime)) {
            throw new ParseException(StartTime.MESSAGE_CONSTRAINTS);
        }
        return new StartTime(trimmedStartTime);
    }

    /**
     * Parses a {@code String endTime} into a {@code EndTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endTime} is invalid.
     */
    public static EndTime parseEndTime(String endTime) throws ParseException {
        requireNonNull(endTime);
        String trimmedEndTime = endTime.trim();
        if (!EndTime.isValidTime(trimmedEndTime)) {
            throw new ParseException(StartTime.MESSAGE_CONSTRAINTS);
        }
        return new EndTime(trimmedEndTime);
    }

    /**
     * Parses a {@code Collection<String> indexes} into a {@code Set<Index>}.
     *
     * @throws ParseException if any of the specified indexes is invalid (not non-zero unsigned integer).
     */
    public static Set<Index> parseParticipants(Collection<String> indexes) throws ParseException {
        requireNonNull(indexes);
        final Set<Index> participantSet = new HashSet<>();
        for (String targetIndex : indexes) {
            participantSet.add(parseIndex(targetIndex));
        }
        return participantSet;
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
     *
     * Parses String days into int days
     * @throws ParseException
     */
    public static int parseDays(String days) throws ParseException {
        requireNonNull(days);

        if (!StringUtil.isNonZeroUnsignedInteger(days.trim())) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Integer.parseInt(days.trim());
    }
}
