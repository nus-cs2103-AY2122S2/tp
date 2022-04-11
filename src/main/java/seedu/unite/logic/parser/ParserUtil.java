package seedu.unite.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_THEME;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.unite.commons.core.index.Index;
import seedu.unite.commons.util.StringUtil;
import seedu.unite.logic.parser.exceptions.ParseException;
import seedu.unite.model.person.Address;
import seedu.unite.model.person.Course;
import seedu.unite.model.person.Email;
import seedu.unite.model.person.MatricCard;
import seedu.unite.model.person.Name;
import seedu.unite.model.person.Phone;
import seedu.unite.model.person.Telegram;
import seedu.unite.model.tag.Tag;
import seedu.unite.ui.theme.DarkTheme;
import seedu.unite.ui.theme.LightTheme;
import seedu.unite.ui.theme.Theme;

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
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Theme parseTheme(String theme) throws ParseException {
        requireNonNull(theme);
        String trimmedTheme = theme.trim();
        if (trimmedTheme.equals("dark")) {
            return new DarkTheme();
        }
        if (trimmedTheme.equals("light")) {
            return new LightTheme();
        }
        throw new ParseException(MESSAGE_INVALID_THEME);
    }

    /**
     * Parses a {@code String courseName} into an {@code Course}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code courseName} is invalid.
     */
    public static Course parseCourse(String courseName) throws ParseException {
        requireNonNull(courseName);
        String trimmedCourseName = courseName.trim();
        if (!Course.isValidCourse(trimmedCourseName)) {
            throw new ParseException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(courseName);
    }

    /**
     * Parses a {@code String cardNumber} into an {@code MatricCard}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cardNumber} is invalid.
     */
    public static MatricCard parseMatricCard(String cardNumber) throws ParseException {
        requireNonNull(cardNumber);
        String trimmedMatricCard = cardNumber.trim();
        if (!MatricCard.isValidMatricCard(trimmedMatricCard)) {
            throw new ParseException(MatricCard.MESSAGE_CONSTRAINTS);
        }
        return new MatricCard(trimmedMatricCard);
    }

    /**
     * Parses a {@code String id} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static Telegram parseTelegram(String id) throws ParseException {
        requireNonNull(id);
        String trimmedTelegram = id.trim();
        if (!Telegram.isValidTelegramId(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
    }
}
