package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.StringUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;

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
     * Leading and trailing whitespaces will be trimmed and converted to lowercase.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        // All tags will be in lowercase
        String trimmedTag = tag.trim().toLowerCase();
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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.contax.model.appointment.Name parseAppointmentName(String name)
            throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.contax.model.appointment.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.contax.model.appointment.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.contax.model.appointment.Name(trimmedName);
    }

    /**
     * Parses a {@code String date} and @{String time} into a {@code StartDateTime}.
     * Parses the date according to the format dd-MM-yyyy.
     * Parses the time according to the format HH:mm.
     *
     * @throws ParseException if the given {@code date} or {@code time} is invalid.
     */
    public static StartDateTime parseStartDateTime(String date, String time) throws ParseException {
        requireAllNonNull(date, time);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime dateTime;
        try {
            dateTime = LocalDate.parse(date, dateFormatter).atStartOfDay();
            LocalTime timeObject = LocalTime.parse(time, timeFormatter);
            dateTime = dateTime.withHour(timeObject.getHour()).withMinute(timeObject.getMinute());
        } catch (DateTimeParseException ex) {
            throw new ParseException(StartDateTime.MESSAGE_CONSTRAINTS);
        }

        return new StartDateTime(dateTime);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     *
     * @throws ParseException if the given {@code date} or {@code time} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireAllNonNull(duration);

        int convertedDuration;
        try {
            convertedDuration = Integer.parseInt(duration);
        } catch (NumberFormatException ex) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }

        if (!Duration.isValidDuration(convertedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }

        return new Duration(convertedDuration);
    }
}
