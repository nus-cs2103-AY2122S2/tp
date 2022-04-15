package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.keyword.Keyword.isValidKeyword;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.keyword.Keyword;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.sort.SortKey;
import seedu.address.logic.commands.sort.SortOrder;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;

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
            Tag tag = parseTag(tagName);
            if (tagSet.stream().noneMatch(tag::equals)) {
                tagSet.add(tag);
            }
        }
        return tagSet;
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        String lowercasePriority = trimmedPriority.toLowerCase();
        if (!Priority.isValidPriority(lowercasePriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }

        return Priority.valueOfLabel(lowercasePriority);
    }

    /**
     * Parses {@code Collection<String> priorities} into a {@code Set<Priority>}.
     */
    public static Set<Priority> parsePriorities(Collection<String> priorities) throws ParseException {
        requireNonNull(priorities);
        final Set<Priority> prioritySet = new HashSet<>();
        for (String priority : priorities) {
            prioritySet.add(parsePriority(priority));
        }
        return prioritySet;
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String keyword} into a {@code Keyword}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Keyword parseKeyword(String keyword) throws ParseException {
        requireNonNull(keyword);
        String trimmedKeyword = keyword.trim();
        if (!isValidKeyword(trimmedKeyword)) {
            throw new ParseException(Keyword.MESSAGE_CONSTRAINTS);
        }
        return new Keyword(trimmedKeyword);
    }

    /**
     * Parses {@code Collection<String> keywords} into a {@code Set<Keyword>}.
     */
    public static Set<Keyword> parseKeywords(Collection<String> keywords) throws ParseException {
        requireNonNull(keywords);
        final Set<Keyword> keywordSet = new HashSet<>();
        for (String keyword : keywords) {
            requireNonNull(keyword);
            String trimmedKeyword = keyword.trim();
            if (!isValidKeyword(trimmedKeyword)) {
                throw new ParseException(Keyword.MESSAGE_CONSTRAINTS);
            }
            keywordSet.add(new Keyword(trimmedKeyword));
        }
        return keywordSet;
    }

    /**
     * Parses a {@code String sortKey} into a {@code SortKey}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortKey} is invalid.
     */
    public static SortKey parseSortKey(String sortKey) throws ParseException {
        requireNonNull(sortKey);
        String trimmedSortKey = sortKey.trim();
        if (!SortKey.isValidSortKey(trimmedSortKey)) {
            throw new ParseException(SortKey.MESSAGE_CONSTRAINTS);
        }
        return SortKey.valueOfLabel(trimmedSortKey);
    }

    /**
     * Parses a {@code String sortOrder} into a {@code SortOrder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sortOrder} is invalid.
     */
    public static SortOrder parseSortOrder(String sortOrder) throws ParseException {
        requireNonNull(sortOrder);
        String trimmedSortOrder = sortOrder.trim();
        if (!SortOrder.isValidSortOrder(trimmedSortOrder)) {
            throw new ParseException(SortOrder.MESSAGE_CONSTRAINTS);
        }
        return SortOrder.valueOfLabel(trimmedSortOrder);
    }

    /**
     * Parses a {@code String completionStatus} into a {@code CompletionStatus}.
     * Leading and trailing whitespaces will be trimmed. Cases are ignored.
     *
     * @param completionStatus a string representing the completion status, should be either "true" or "false"
     *
     * @throws ParseException if the given {@code completionStatus} is invalid.
     */
    public static CompletionStatus parseCompletionStatus(String completionStatus) throws ParseException {

        requireNonNull(completionStatus);
        String trimmedCompletionStatus = completionStatus.trim();
        String lowercaseCompletionStatus = trimmedCompletionStatus.toLowerCase();
        if (!CompletionStatus.isValidCompletionStatus(lowercaseCompletionStatus)) {
            throw new ParseException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }

        return new CompletionStatus(lowercaseCompletionStatus);
    }
}
