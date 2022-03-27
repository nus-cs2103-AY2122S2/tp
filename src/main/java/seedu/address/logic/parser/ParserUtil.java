package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a {@code String githubUsername} into an {@code GithubUsername}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code githubUsername} is invalid.
     */
    public static GithubUsername parseGithubUsername(String githubUsername) throws ParseException {
        requireNonNull(githubUsername);
        String trimmedUsername = githubUsername.trim();

        if (!GithubUsername.isValidGithubUsername(trimmedUsername)) {
            throw new ParseException(GithubUsername.MESSAGE_CONSTRAINTS);
        }

        return new GithubUsername(trimmedUsername);
    }

    /**
     * Parses a {@code String telegram} into an {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        requireNonNull(telegram);
        String trimmedTelegram = telegram.trim();

        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }

        return new Telegram(trimmedTelegram);
    }

    /**
     * Parses a {@code String studentId} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     * Input will be converted to uppercase.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedId = studentId.trim();
        String capitalId = trimmedId.toUpperCase();

        if (!StudentId.isValidStudentId(capitalId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }

        return new StudentId(capitalId);
    }

    /**
     * Parses a {@code String labNumber} into an {@code Lab}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code labNumber} is invalid.
     */
    public static Lab parseLab(String labNumber) throws ParseException {
        requireNonNull(labNumber);
        String trimmedLab = labNumber.trim();

        if (!Lab.isValidLab(trimmedLab)) {
            throw new ParseException(Lab.MESSAGE_CONSTRAINTS);
        }

        return new Lab(trimmedLab);
    }

    /**
     * Parses a {@code String labStatus} into a {@code LabStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code labStatus} is invalid.
     */
    public static LabStatus parseLabStatus(String labStatus) throws ParseException {
        requireNonNull(labStatus);
        String trimmedLabStatus = labStatus.trim();

        if (!LabStatus.isValidLabStatus(trimmedLabStatus)) {
            throw new ParseException(LabStatus.MESSAGE_CONSTRAINTS);
        }

        switch (trimmedLabStatus) {
        case "u":
            return LabStatus.UNSUBMITTED;
        case "s":
            return LabStatus.SUBMITTED;
        case "g":
            return LabStatus.GRADED;
        default:
            throw new ParseException(Lab.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String labMark} into a {@code LabMark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code labMark} is invalid.
     */
    public static LabMark parseLabMark(String labMark) throws ParseException {
        requireNonNull(labMark);
        String trimmedLabMark = labMark.trim();

        if (!LabMark.isValidLabMark(trimmedLabMark)) {
            throw new ParseException(LabMark.MESSAGE_CONSTRAINTS);
        }

        return new LabMark(trimmedLabMark);
    }
}
