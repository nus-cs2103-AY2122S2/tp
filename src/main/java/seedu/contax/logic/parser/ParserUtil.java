package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.CollectionUtil.requireAllNonNull;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.DateUtil;
import seedu.contax.commons.util.StringUtil;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.IndexedCsvFile;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.person.Address;
import seedu.contax.model.person.Email;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.Phone;
import seedu.contax.model.tag.Tag;
import seedu.contax.model.util.SearchType;

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
            throw new ParseException(seedu.contax.model.tag.Name.MESSAGE_CONSTRAINTS);
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
     * Parses a {@code String filePath} into a {@code Path}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filePath} is invalid.
     */
    public static File parseCsvFilePath(String filePath) throws ParseException {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();
        if (!IndexedCsvFile.isValidFilePath(trimmedFilePath)) {
            throw new ParseException(IndexedCsvFile.FILE_PATH_CONSTRAINTS);
        }
        return new File(trimmedFilePath);
    }

    /**
     * Parses a {@String position} into an {@code integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static int parseCsvPositions(String positionString) throws ParseException {
        requireNonNull(positionString);
        String trimmedPosition = positionString.trim();
        try {
            int finalPosition = Integer.parseInt(trimmedPosition);
            if (finalPosition < 1) {
                throw new ParseException(IndexedCsvFile.MESSAGE_CONSTRAINTS);
            }
            return finalPosition;
        } catch (NumberFormatException e) {
            throw new ParseException(IndexedCsvFile.MESSAGE_CONSTRAINTS);
        }
    }
    /**
     * Parses a {@code String searchType} into an {@code type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static SearchType parseSearchType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedName = type.trim();
        if (!SearchType.isValidType(trimmedName)) {
            throw new ParseException(SearchType.SEARCH_TYPE_CONSTRAINTS);
        }
        return new SearchType(trimmedName);
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

        LocalDate dateObject = DateUtil.parseDate(date)
                .orElseThrow(() -> new ParseException(StartDateTime.MESSAGE_CONSTRAINTS));
        LocalTime timeObject = DateUtil.parseTime(time)
                .orElseThrow(() -> new ParseException(StartDateTime.MESSAGE_CONSTRAINTS));
        LocalDateTime combinedDateTime = DateUtil.combineDateTime(dateObject, timeObject);

        return new StartDateTime(combinedDateTime);
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

    /**
     * Parses a {@code String commandInput} into a {@code output} that command with index.
     *
     * @throws ParseException if the given {@code commandInput} is empty.
     */
    public static String parseAndCreateNewCommand(String commandInput, String index) throws ParseException {
        if (commandInput.trim().isEmpty()) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        StringBuilder output = new StringBuilder();
        commandInput = commandInput.trim();
        String[] splitCommand = commandInput.split(" ");
        output.append(splitCommand[0]).append(" ").append(index);
        if (commandInput.contains(" ")) {
            output.append(commandInput.substring(splitCommand[0].length()));
        }
        return output.toString();
    }
}
