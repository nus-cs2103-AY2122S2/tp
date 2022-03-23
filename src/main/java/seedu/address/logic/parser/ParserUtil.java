package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Priority;
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
     * Parses {@code indexAndWords} into an {@code Pair<Index, String>} and returns it. Leading and trailing
     * whitespaces will be trimmed. Must contain a number at the beginning and then words.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Pair<Index, String> parseOutIndex(String indexAndWords) throws ParseException {
        String trimmedInput = indexAndWords.trim();
        // splitIndexAndRemainingString[0] contains index no., splitIndexAndRemainingString[2] contains remaining string
        String[] splitIndexAndRemainingString = trimmedInput.split(" ", 2);
        assert splitIndexAndRemainingString.length == 2;

        Index index = parseIndex(splitIndexAndRemainingString[0]);
        return new Pair<>(index, splitIndexAndRemainingString[1]);
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
     * Parses a {@code String insurance package} into an {@code InsurancePackage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static InsurancePackage parseInsurancePackage(String insurancePackage) throws ParseException {
        requireNonNull(insurancePackage);
        String trimmedInsurancePackage = insurancePackage.trim();
        if (!InsurancePackage.isValidInsurancePackage(trimmedInsurancePackage)) {
            throw new ParseException(InsurancePackage.MESSAGE_CONSTRAINTS);
        }
        return new InsurancePackage(trimmedInsurancePackage);
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
     * Parses a {@code String tag} into a {@code Pair<String, Priority>}.
     * Seperates the priority from the tag entered
     */
    public static Pair<String, Priority> parsePriority(String tag) throws ParseException {
        requireNonNull(tag);
        Priority priority = null;
        String tagName = "";
        String possiblePriority = tag.length() > 4 ? tag.substring(tag.length() - 4).toLowerCase() : "";

        switch (possiblePriority) {
        case " :p1":
            tagName = tag.substring(0, tag.length() - 4);
            priority = Priority.PRIORITY_1;
            break;
        case " :p2":
            tagName = tag.substring(0, tag.length() - 4);
            priority = Priority.PRIORITY_2;
            break;
        case " :p3":
            tagName = tag.substring(0, tag.length() - 4);
            priority = Priority.PRIORITY_3;
            break;
        case " :p4":
            tagName = tag.substring(0, tag.length() - 4);
            priority = Priority.PRIORITY_4;
            break;
        default:
            tagName = tag;
            priority = null;
        }
        return new Pair<>(tagName, priority);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        Pair<String, Priority> tagAndPriority = parsePriority(tag.trim());
        String trimmedTag = tagAndPriority.getKey().trim();

        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag, tagAndPriority.getValue());
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
