package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Phone;
import seedu.address.model.medical.Age;
import seedu.address.model.medical.BloodType;
import seedu.address.model.medical.Ethnicity;
import seedu.address.model.medical.FamilyHistory;
import seedu.address.model.medical.Gender;
import seedu.address.model.medical.Height;
import seedu.address.model.medical.Illnesses;
import seedu.address.model.medical.ImmunizationHistory;
import seedu.address.model.medical.Medication;
import seedu.address.model.medical.Surgeries;
import seedu.address.model.medical.Weight;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
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
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
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
     * Parses a {@code String age} into an {@code Age}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        if (!Age.isValidAge(age)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(age.trim());
    }

    /**
     * Parses a {@code String bloodType} into a {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(bloodType.trim());
    }

    /**
     * Parses a {@code String medication} into a {@code Medication}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Medication parseMedication(String medication) throws ParseException {
        requireNonNull(medication);
        if (!Medication.isValidMedication(medication)) {
            throw new ParseException(Medication.MESSAGE_CONSTRAINTS);
        }
        return new Medication(medication.trim());
    }

    /**
     * Parses a {@code String height} into a {@code Height}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        if (!Height.isValidHeight(height)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS);
        }
        return new Height(height.trim());
    }


    /**
     * Parses a {@code String weight} into a {@code Weight}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        if (!Weight.isValidWeight(weight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS);
        }
        return new Weight(weight.trim());
    }

    /**
     * Parses a {@code String illnesses} into a {@code Illnesses}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Illnesses parseIllnesses(String illnesses) throws ParseException {
        requireNonNull(illnesses);
        if (!Illnesses.isValidIllnesses(illnesses)) {
            throw new ParseException(Illnesses.MESSAGE_CONSTRAINTS);
        }
        return new Illnesses(illnesses.trim());
    }

    /**
     * Parses a {@code String surgeries} into a {@code Surgeries}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Surgeries parseSurgeries(String surgeries) throws ParseException {
        requireNonNull(surgeries);
        if (!Surgeries.isValidSurgeries(surgeries)) {
            throw new ParseException(Surgeries.MESSAGE_CONSTRAINTS);
        }
        return new Surgeries(surgeries.trim());
    }

    /**
     * Parses a {@code String familyHistory} into a {@code FamilyHistory}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static FamilyHistory parseFamilyHistory(String familyHistory) throws ParseException {
        requireNonNull(familyHistory);
        if (!FamilyHistory.isValidFamilyHistory(familyHistory)) {
            throw new ParseException(FamilyHistory.MESSAGE_CONSTRAINTS);
        }
        return new FamilyHistory(familyHistory.trim());
    }

    /**
     * Parses a {@code String immunizationHistory} into a {@code ImmunizationHistory}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ImmunizationHistory parseImmunizationHistory(String immunizationHistory) throws ParseException {
        requireNonNull(immunizationHistory);
        if (!FamilyHistory.isValidFamilyHistory(immunizationHistory)) {
            throw new ParseException(ImmunizationHistory.MESSAGE_CONSTRAINTS);
        }
        return new ImmunizationHistory(immunizationHistory.trim());
    }


    /**
     * Parses a {@code String gender} into a {@code Gender}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        if (!Gender.isValidGender(gender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(gender.trim());
    }

    /**
     * Parses a {@code String ethnicity} into a {@code Ethnicity}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Ethnicity parseEthnicity(String ethnicity) throws ParseException {
        requireNonNull(ethnicity);
        if (!Gender.isValidGender(ethnicity)) {
            throw new ParseException(Ethnicity.MESSAGE_CONSTRAINTS);
        }
        return new Ethnicity(ethnicity.trim());
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
}
