package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.schedule.Event.DATE_MESSAGE_CONSTRAINTS;
import static seedu.address.model.schedule.Event.DURATION_MESSAGE_CONSTRAINTS;
import static seedu.address.model.schedule.Event.TIME_MESSAGE_CONSTRAINTS;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.schedule.EventDescription;
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
     * Parses {@code oneBasedIndices} into a {@code List<Index>} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if any specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndices (String oneBasedIndices) throws ParseException {
        String[] indices = oneBasedIndices.split(" ");
        ArrayList<Index> trimmedIndices = new ArrayList<>();
        for (String index : indices) {
            if (!StringUtil.isNonZeroUnsignedInteger(index.trim())) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            trimmedIndices.add(Index.fromOneBased(Integer.parseInt(index)));
        }
        return trimmedIndices;
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

    public static Telegram parseTelegram(String username) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!Telegram.isValidUsername(trimmedUsername)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(username);
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
        if (Address.isEmptyAddress(trimmedAddress)) {
            return Address.DEFAULT_ADDRESS;
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
        if (Email.isEmptyEmail(trimmedEmail)) {
            return Email.DEFAULT_EMAIL;
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String eventDescription} into an {@code EventDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventDescription} is invalid.
     */
    public static EventDescription parseEventDescription(String eventDescription) throws ParseException {
        requireNonNull(eventDescription);
        String trimmedEventDescription = eventDescription.trim();
        if (!EventDescription.isValidEventDescription(trimmedEventDescription)) {
            throw new ParseException(EventDescription.MESSAGE_CONSTRAINTS);
        }
        return new EventDescription(eventDescription);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return LocalDate.parse(trimmedDate);
        } catch (DateTimeParseException e) {
            throw new ParseException(DATE_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String time} into an {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        try {
            return LocalTime.parse(trimmedTime);
        } catch (DateTimeParseException e) {
            throw new ParseException(TIME_MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String duration} into an {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim().toUpperCase();
        try {
            String[] splitDuration = trimmedDuration.split("H");
            int hours = Integer.parseInt(splitDuration[0]);
            int minutes = 0;
            if (splitDuration.length > 1) {
                minutes = Integer.parseInt(splitDuration[1].split("M")[0]);
            }
            return Duration.ofHours(hours).plusMinutes(minutes);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new ParseException(DURATION_MESSAGE_CONSTRAINTS);
        }
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
}
