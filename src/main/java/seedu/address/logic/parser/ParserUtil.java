package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.TYPE_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.entity.EntityType;
import seedu.address.model.person.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

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

    //@@author EvaderFati
    /**
     * Parses a {@code String id} into an {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code StudentId} is invalid.
     */
    public static StudentId parseStudentId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!StudentId.isValidStudentId(trimmedId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedId);
    }

    //@@author EvaderFati
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

    //@@author EvaderFati
    /**
     * Parses a {@code Optional<String> telegram} into a {@code Optional<Telegram>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Optional<Telegram> parseTelegram(Optional<String> telegram) throws ParseException {
        if (telegram.isEmpty()) {
            return Optional.empty();
        }
        String trimmedTelegram = telegram.get().trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Telegram(trimmedTelegram));
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

    //@@author EvaderFati
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

    //@@author EvaderFati
    /**
     * Parses a {@code String moduleName} into an {@code ModuleName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} is invalid.
     */
    public static ModuleName parseModuleName(String moduleName) throws ParseException {
        requireNonNull(moduleName);
        String trimmedModuleName = moduleName.trim();
        if (!ModuleName.isValidModuleName(trimmedModuleName)) {
            throw new ParseException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        return new ModuleName(trimmedModuleName);
    }

    //@@author EvaderFati
    /**
     * Parses a {@code String moduleCode} into an {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    //@@author EvaderFati
    /**
     * Parses a {@code String academicYear} into an {@code AcademicYear}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code academicYear} is invalid.
     */
    public static AcademicYear parseAcademicYear(String academicYear) throws ParseException {
        requireNonNull(academicYear);
        String trimmedAcademicYear = academicYear.trim();
        if (!AcademicYear.isValidAcademicYear(trimmedAcademicYear)) {
            throw new ParseException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        return new AcademicYear(trimmedAcademicYear);
    }

    //@@author EvaderFati
    /**
     * Parses a {@code String index} into an {@code TaModule}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code academicYear} is invalid.
     */
    public static TaModule parseTaModule(String index, Model model) throws ParseException {
        requireNonNull(index);
        Index targetIndex = parseIndex(index);
        List<TaModule> lastShownModuleList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownModuleList.size()) {
            throw new ParseException(Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
        }

        return lastShownModuleList.get(targetIndex.getZeroBased());
    }

    //@@author EvaderFati
    /**
     * Parses a {@code String classGroupId} into an {@code ClassGroupId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code classGroupId} is invalid.
     */
    public static ClassGroupId parseClassGroupId(String classGroupId) throws ParseException {
        requireNonNull(classGroupId);
        String trimmedId = classGroupId.trim();
        if (!ClassGroupId.isValidClassGroupId(trimmedId)) {
            throw new ParseException(ClassGroupId.MESSAGE_CONSTRAINTS);
        }
        return new ClassGroupId(trimmedId);
    }

    //@@author EvaderFati
    /**
     * Parses a {@code String classGroupType} into an {@code ClassGroupType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code classGroupType} is invalid.
     */
    public static ClassGroupType parseClassGroupType(String classGroupType) throws ParseException {
        requireNonNull(classGroupType);
        String trimmedUpperCaseType = classGroupType.trim().toUpperCase();
        try {
            ClassGroupType type = ClassGroupType.valueOf(trimmedUpperCaseType);
            return type;
        } catch (IllegalArgumentException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }

    /**
     * Parses a {@code String entityType} into an {@code EntityType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code entityType} is invalid.
     */
    public static EntityType parseEntity(String entityType) throws ParseException {
        String trimmedEntityType = entityType.trim();
        switch(trimmedEntityType) {
        case TYPE_STUDENT:
            return EntityType.STUDENT;
        case TYPE_MODULE:
            return EntityType.TA_MODULE;
        case TYPE_CLASS:
            return EntityType.CLASS_GROUP;
        case TYPE_ASSESSMENT:
            return EntityType.ASSESSMENT;
        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_ENTITY);
        }
    }

    /**
     * Parses a {@code String assessmentName} into a {@code AssessmentName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code assessmentName} is invalid.
     */
    public static AssessmentName parseAssessmentName(String assessmentName) throws ParseException {
        requireNonNull(assessmentName);
        String trimmedName = assessmentName.trim();
        if (!AssessmentName.isValidAssessmentName(trimmedName)) {
            throw new ParseException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        return new AssessmentName(trimmedName);
    }

    /**
     * Parses a {@code String simpleName} into a {@code SimpleName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code simpleName} is invalid.
     */
    public static Optional<SimpleName> parseSimpleName(Optional<String> simpleName) throws ParseException {
        if (simpleName.isEmpty()) {
            return Optional.empty();
        }
        requireNonNull(simpleName);
        String trimmedName = simpleName.get().trim();
        if (!SimpleName.isValidSimpleName(trimmedName)) {
            throw new ParseException(SimpleName.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new SimpleName(trimmedName));
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
}
