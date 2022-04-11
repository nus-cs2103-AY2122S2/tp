package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.IsRecurring;
import seedu.address.model.meeting.MeetingDateTime;
import seedu.address.model.meeting.MeetingDuration;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.MeetingUrl;
import seedu.address.model.module.Module;
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
        return parseIndexWithErrMsg(oneBasedIndex, MESSAGE_INVALID_INDEX);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed. If the operation fails, a {@code ParseException} indicating the {@code indexType} will be thrown.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex, String indexType) throws ParseException {
        final String errMsg = String.format("%s %s", processIndexType(indexType), MESSAGE_INVALID_INDEX).trim();
        return parseIndexWithErrMsg(oneBasedIndex, errMsg);
    }

    private static Index parseIndexWithErrMsg(String oneBasedIndex, String errMsg) throws ParseException {
        final String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(errMsg);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    private static String processIndexType(String indexType) {
        final String trimmed = indexType.trim();
        if (trimmed.isEmpty()) {
            return trimmed;
        }
        return Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1).toLowerCase();
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
        if (!Tag.isValidTagLength(trimmedTag)) {
            throw new ParseException(String.format(Tag.MESSAGE_LENGTH_EXCEEDED, Tag.MAX_TAG_LENGTH));
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
     * Parses a {@code String meetingName} into a {@code MeetingName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingName} is invalid.
     */
    public static MeetingName parseMeetingName(String meetingName) throws ParseException {
        requireNonNull(meetingName);
        String trimmedMeetingName = meetingName.trim();
        if (!MeetingName.isValidName(trimmedMeetingName)) {
            throw new ParseException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        return new MeetingName(trimmedMeetingName);
    }

    /**
     * Parses a {@code String meetingUrl} into a {@code MeetingUrl}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingUrl} is invalid.
     */
    public static MeetingUrl parseMeetingUrl(String meetingUrl) throws ParseException {
        requireNonNull(meetingUrl);
        String trimmedMeetingUrl = meetingUrl.trim();
        if (!MeetingUrl.isValidUrl(trimmedMeetingUrl)) {
            throw new ParseException(MeetingUrl.MESSAGE_CONSTRAINTS);
        }
        return new MeetingUrl(trimmedMeetingUrl);
    }

    /**
     * Parses a {@code String meetingDateTime} into a {@code MeetingDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingDateTime} is invalid.
     */
    public static MeetingDateTime parseMeetingDateTime(String meetingDateTime) throws ParseException {
        requireNonNull(meetingDateTime);
        String trimmedMeetingDateTime = meetingDateTime.trim();
        if (!MeetingDateTime.isValidDateTime(trimmedMeetingDateTime)) {
            throw new ParseException(MeetingDateTime.MESSAGE_CONSTRAINTS);
        }
        return new MeetingDateTime(trimmedMeetingDateTime);
    }

    /**
     * Parses a {@code String meetingDuration} into a {@code MeetingDuration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code meetingDuration} is invalid.
     */
    public static MeetingDuration parseMeetingDuration(String meetingDuration) throws ParseException {
        requireNonNull(meetingDuration);
        final String trimmedMeetingDuration = meetingDuration.trim();
        if (!MeetingDuration.isValidDuration(trimmedMeetingDuration)) {
            throw new ParseException(MeetingDuration.MESSAGE_CONSTRAINTS);
        }
        return new MeetingDuration(trimmedMeetingDuration);
    }

    /**
     * Parses a {@code String module} into a {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        String trimmedModule = module.trim();
        if (!Module.isValidModule(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        return new Module(trimmedModule);
    }

    /**
     * Parses a {@code String recurringStatus} into a {@code IsRecurring}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code recurringStatus} is invalid.
     */
    public static IsRecurring parseRecurringStatus(String recurringStatus) throws ParseException {
        requireNonNull(recurringStatus);
        String trimmedRecurringStatus = recurringStatus.trim();
        if (!IsRecurring.isValidRecurringStatus(trimmedRecurringStatus)) {
            throw new ParseException(IsRecurring.MESSAGE_CONSTRAINTS);
        }
        return new IsRecurring(trimmedRecurringStatus);
    }
}
