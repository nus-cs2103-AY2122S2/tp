package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.JerseyNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.schedule.ScheduleDateTime;
import seedu.address.model.schedule.ScheduleDescription;
import seedu.address.model.schedule.ScheduleName;
import seedu.address.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses a player.
     */
    public static Name parsePlayer(String targetPlayerName) throws ParseException {
        requireNonNull(targetPlayerName);
        String trimmedName = targetPlayerName.trim();
        return new Name(trimmedName);
    }

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
     * Parses a {@code String height} into an {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(height)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(trimmedHeight);
    }

    /**
     * Parses a {@code String weight} into an {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(weight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(trimmedWeight);
    }

    /**
     * Parses a {@code String jerseyNumber} into an {@code JerseyNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static JerseyNumber parseJerseyNumber(String jerseyNumber) throws ParseException {
        requireNonNull(jerseyNumber);
        String trimmedJerseyNumber = jerseyNumber.trim();
        if (!JerseyNumber.isValidJerseyNumber(trimmedJerseyNumber)) {
            throw new ParseException(JerseyNumber.MESSAGE_CONSTRAINTS);
        }
        return new JerseyNumber(trimmedJerseyNumber);
    }

    /**
     * Parses a {@code String LineupName} into an {@code LineupName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException
     */
    public static LineupName parseLineupName(String lineupName) throws ParseException {
        requireNonNull(lineupName);
        String trimmedLineupName = lineupName.trim();
        if (!LineupName.isValidLineupName(trimmedLineupName)) {
            throw new ParseException(LineupName.MESSAGE_CONSTRAINTS);
        }
        return new LineupName(trimmedLineupName);
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

    // Parse Schedule related attributes
    /**
     * Parses a {@code String scheduleName} into a {@code ScheduleName}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code scheduleName} is invalid.
     */
    public static ScheduleName parseScheduleName(String scheduleName) throws ParseException {
        requireNonNull(scheduleName);
        String trimmedName = scheduleName.trim();
        if (!ScheduleName.isValidScheduleName(trimmedName)) {
            throw new ParseException(ScheduleName.MESSAGE_CONSTRAINTS);
        }
        return new ScheduleName(trimmedName);
    }

    /**
     * Parses a {@code String scheduleDescription} into a {@code ScheduleDescription}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code scheduleDescription} is invalid.
     */
    public static ScheduleDescription parseScheduleDescription(String scheduleDescription) throws ParseException {
        requireNonNull(scheduleDescription);
        String trimmedDescription = scheduleDescription.trim();
        if (!ScheduleDescription.isValidScheduleDescription(trimmedDescription)) {
            throw new ParseException(ScheduleDescription.MESSAGE_CONSTRAINTS);
        }
        return new ScheduleDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String scheduleDateTime} into a {@code ScheduleDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code scheduleDateTime} is invalid.
     */
    public static ScheduleDateTime parseScheduleDateTime(String scheduleDateTime) throws ParseException {
        requireNonNull(scheduleDateTime);
        String trimmedDateTime = scheduleDateTime.trim();
        if (!ScheduleDateTime.isValidScheduleDateTime(trimmedDateTime)) {
            throw new ParseException(ScheduleDateTime.MESSAGE_CONSTRAINTS);
        }
        return new ScheduleDateTime(trimmedDateTime);
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
