package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Birthday;
import seedu.address.model.client.Email;
import seedu.address.model.client.LastContacted;
import seedu.address.model.client.Name;
import seedu.address.model.client.Note;
import seedu.address.model.client.Phone;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.policy.Premium;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String DEFAULT_DATE = "01-01-0001";
    public static final String DEFAULT_DATETIME = "01-01-0001 00:00";

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
        if (address == null) {
            return new Address();
        }
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String date} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Birthday parseBirthday(String date) throws ParseException {
        if (date == null) {
            return new Birthday();
        }
        String trimmedBirthday = date.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        if (!Birthday.isPastBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_FUTURE_DATE);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String dateTime} into a {@code LastContacted}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static LastContacted parseLastContacted(String dateTime) throws ParseException {
        if (dateTime == null) {
            return new LastContacted();
        }
        String trimmedLastContacted = dateTime.trim();
        if (!LastContacted.isValidLastContacted(trimmedLastContacted)) {
            throw new ParseException(LastContacted.MESSAGE_CONSTRAINTS);
        }
        if (!LastContacted.isPastLastContacted(trimmedLastContacted)) {
            throw new ParseException(LastContacted.MESSAGE_FUTURE_DATETIME);
        }
        return new LastContacted(trimmedLastContacted);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        if (email == null) {
            return new Email();
        }
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String input} into an {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static LocalDateTime parseDateTime(String stringDateTime) throws ParseException {
        requireNonNull(stringDateTime);
        String trimmedStringDateTime = stringDateTime.trim();

        if (!Meeting.isValidDate(trimmedStringDateTime)) {
            throw new ParseException(Meeting.DATETIME_MESSAGE_CONSTRAINTS);
        } else if (!Meeting.isValidDateRange(trimmedStringDateTime)) {
            throw new ParseException(Meeting.DATETIME_MESSAGE_BAD_RANGE);
        }

        return LocalDateTime.parse(trimmedStringDateTime, Meeting.DATETIME_FORMATTER);
    }

    /**
     * Parses a {@code String premium} into a {@code Premium}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code premium} is invalid.
     */
    public static Premium parsePremium(String premium) throws ParseException {
        requireNonNull(premium);
        String trimmedPremium = premium.trim();
        if (!Premium.isValidPremium(trimmedPremium)) {
            throw new ParseException(Premium.MESSAGE_CONSTRAINTS);
        }
        return new Premium(trimmedPremium);
    }

    /**
     * Parses a {@code String note} into a {@code Note}
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Note parseNote(String note) {
        requireNonNull(note);
        String trimmedNote = note.trim();
        return new Note(trimmedNote);
    }
}
