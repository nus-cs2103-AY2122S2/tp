package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.image.Image;
import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.strategy.Player;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_NOTE_INDEX = "Note index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_IMAGE = "Image does not exit.";

    public static final String MESSAGE_INVALID_IMAGE_NAME = "Image should not contain \'/\' characters.";

    public static final String MESSAGE_DIRECTORY_NOT_EXIST = "The " + Image.FILE_PATH + " directory does not exist!";

    public static final String MESSAGE_INVALID_KEYWORD = "Keywords should be alphanumeric and should not be blank.";
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
     * Parses {@code imageName} into a {@code Image} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified imageName is invalid.
     */
    public static Image parseImage(String imageName) throws ParseException {
        requireNonNull(imageName);
        String trimmedName = imageName.trim();
        File folder = new File(Image.FILE_PATH);
        if (!folder.exists()) {
            throw new ParseException(MESSAGE_DIRECTORY_NOT_EXIST);
        }
        if (trimmedName.contains("/")) {
            throw new ParseException(MESSAGE_INVALID_IMAGE_NAME);
        }
        if (!Image.isValidFile(trimmedName)) {
            throw new ParseException(MESSAGE_INVALID_IMAGE);
        }
        return new Image(trimmedName);
    }

    /**
     * Parses {@code oneBasedNoteIndex} into an {@code NoteIndex} and returns it. Leading and trailing whitespaces
     * will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseNoteIndex(String oneBasedNoteIndex) throws ParseException {
        String trimmedNoteIndex = oneBasedNoteIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedNoteIndex)) {
            throw new ParseException(MESSAGE_INVALID_NOTE_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedNoteIndex));
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
     * Parses a {@code String nameKeyword} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nameKeyword} is invalid or contain spaces.
     */
    public static Name parseNameKeyword(String nameKeyword) throws ParseException {
        requireNonNull(nameKeyword);
        String trimmedName = nameKeyword.trim();
        if (!Name.isValidName(trimmedName) || trimmedName.split("\\s+").length != 1) {
            throw new ParseException(MESSAGE_INVALID_KEYWORD);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses {@code Collection<String> names} into a {@code Set<Name>}.
     */
    public static Set<Name> parseNames(Collection<String> names) throws ParseException {
        requireNonNull(names);
        final Set<Name> nameSet = new HashSet<>();
        for (String name : names) {
            String trimmedName = name.trim();
            if (!Name.isValidName(trimmedName)) {
                throw new ParseException(Name.MESSAGE_CONSTRAINTS);
            }
            nameSet.add(new Name(name));
        }
        return nameSet;
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
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String startTime} into an {@code StartTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static StartTime parseStartTime(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!StartTime.isValidStartTime(trimmedStartTime)) {
            throw new ParseException(StartTime.MESSAGE_CONSTRAINTS);
        }
        return new StartTime(trimmedStartTime);
    }

    /**
     * Parses a {@code String endTime} into an {@code EndTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endTime} is invalid.
     */
    public static EndTime parseEndTime(String endTime) throws ParseException {
        requireNonNull(endTime);
        String trimmedEndTime = endTime.trim();
        if (!EndTime.isValidEndTime(trimmedEndTime)) {
            throw new ParseException(EndTime.MESSAGE_CONSTRAINTS);
        }
        return new EndTime(trimmedEndTime);
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
     * Parses a {@code String tagKeyword} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagKeyword} is invalid.
     */
    public static Tag parseTagKeyword(String tagKeyword) throws ParseException {
        requireNonNull(tagKeyword);
        String trimmedTag = tagKeyword.trim();
        if (!Tag.isValidTagName(trimmedTag) || trimmedTag.split("\\s+").length != 1) {
            throw new ParseException(MESSAGE_INVALID_KEYWORD);
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
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String playerName} into a {@code Player}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code playerName} is invalid.
     */
    public static Player paresPlayer(String playerName) throws ParseException {
        requireNonNull(playerName);
        String trimmedPlayerName = playerName.trim();
        if (!Player.isValidPlayer(trimmedPlayerName)) {
            throw new ParseException(Player.MESSAGE_NAME_CONSTRAINTS);
        }
        return new Player(trimmedPlayerName);
    }
}
