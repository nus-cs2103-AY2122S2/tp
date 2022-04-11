package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentNumber;
import seedu.address.model.tag.Tag;
import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;

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
     * Parses a {@code String studentNumber} into a {@code StudentNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentNumber} is invalid.
     */
    public static StudentNumber parseStudentNumber(String studentNumber) throws ParseException {
        requireNonNull(studentNumber);
        String trimmedStudentNumber = studentNumber.trim();
        if (!StudentNumber.isValidStudentNumber(trimmedStudentNumber)) {
            throw new ParseException(StudentNumber.MESSAGE_CONSTRAINTS);
        }
        return new StudentNumber(trimmedStudentNumber);
    }

    /**
     * Parses a {@code String mod} into an {@code Mod}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mod} is invalid.
     */
    public static Mod parseMod(String mod) throws ParseException {
        requireNonNull(mod);
        String trimmedCode = mod.trim();
        if (!Mod.isValidMod(trimmedCode)) {
            throw new ParseException(Mod.MESSAGE_CONSTRAINTS);
        }
        return new Mod(trimmedCode);
    }


    /**
     * Parses a {@code String group} into an {@code Group}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code group} is invalid.
     */
    public static Group parseGroup(String group) throws ParseException {
        requireNonNull(group);
        if (group == "") {
            return Group.EMPTY_GROUP;
        }
        //if(isNull(group))
        String trimmedGroupId = group.trim();
        if (!Group.isValidGroup(trimmedGroupId)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedGroupId);
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
     * Parses a {@code String desc} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code desc} is invalid.
     */
    public static Description parseDesc(String desc) throws ParseException {
        requireNonNull(desc);
        String trimmedDesc = desc.trim();
        if (!Description.isValidDesc(trimmedDesc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDesc);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
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
}
