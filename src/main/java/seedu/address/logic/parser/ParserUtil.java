package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.Address;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Email;
import seedu.address.model.entry.Location;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Phone;
import seedu.address.model.entry.Time;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_SEARCH_TYPE = "Search Type must be one of the following: "
            + "UNARCHIVED, ARCHIVED, ALL";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.replaceAll("\\s{2,}", " ").trim();
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
        String trimmedName = name.replaceAll("\\s{2,}", " ").trim();
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
        String trimmedPhone = phone.replaceAll("\\s{2,}", " ").trim();
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
        String trimmedAddress = address.replaceAll("\\s{2,}", " ").trim();
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
        String trimmedEmail = email.replaceAll("\\s{2,}", " ").trim();
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
        String trimmedTag = tag.replaceAll("\\s{2,}", " ").trim();
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
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */

    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.replaceAll("\\s{2,}", " ").trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String time} into an {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */

    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.replaceAll("\\s{2,}", " ").trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.replaceAll("\\s{2,}", " ").trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String searchType} into a {@code SearchType}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code searchType} is invalid.
     */
    public static SearchType parseSearchType(String searchType) throws ParseException {
        requireNonNull(searchType);
        String trimmedSearchType = searchType.replaceAll("\\s{2,}", " ").trim();

        switch (trimmedSearchType) {
        case "unarchived":
            return SearchType.UNARCHIVED_ONLY;
        case "archived":
            return SearchType.ARCHIVED_ONLY;
        case "all":
            return SearchType.ALL;
        default:
            throw new ParseException(MESSAGE_INVALID_SEARCH_TYPE);
        }
    }

    /**
     * Parses a {@code String ordering} into an {@code Ordering}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ordering} is invalid.
     */
    public static Ordering parseOrdering(String ordering) throws ParseException {
        requireNonNull(ordering);
        String trimmedOrdering = ordering.replaceAll("\\s{2,}", " ").trim();

        switch (trimmedOrdering) {
        case "ascending":
            return Ordering.ASCENDING;
        case "descending":
            return Ordering.DESCENDING;
        default:
            throw new ParseException(MESSAGE_INVALID_SEARCH_TYPE);
        }
    }
}
