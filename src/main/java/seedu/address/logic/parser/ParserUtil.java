package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.predicates.AddressContainsKeywordsPredicate;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;
import seedu.address.model.person.predicates.InsurancePackageContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;
import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final Logger logger = LogsCenter.getLogger(ParserUtil.class);

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
        String[] splitIndexAndRemainingString;
        Index index;

        try {
            splitIndexAndRemainingString = trimmedInput.split(" ", 2);
            index = parseIndex(splitIndexAndRemainingString[0]);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new Pair<>(index, splitIndexAndRemainingString.length > 1 ? splitIndexAndRemainingString[1].trim() : "");
    }

    /**
     * Parses {@code numberAndWords} into an {@code Pair<Integer, String>} and returns it. Leading and trailing
     * whitespaces will be trimmed. Must contain a number at the beginning and then words.
     *
     * @throws ParseException if the specified integer is invalid (not non-zero unsigned integer).
     */
    public static Pair<Integer, String> parseOutNumber(String numberAndWords) throws ParseException {
        String trimmedInput = numberAndWords.trim();

        // splitIndexAndRemainingString[0] contains number, splitIndexAndRemainingString[2] contains remaining string
        String[] splitIndexAndRemainingString;
        Integer number;

        try {
            splitIndexAndRemainingString = trimmedInput.split(" ", 2);
            number = Integer.valueOf(splitIndexAndRemainingString[0].trim());
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new Pair<>(number, splitIndexAndRemainingString.length > 1
                ? splitIndexAndRemainingString[1].trim() : "");
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
     * Parses a {@code String insurance package name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static String parseInsurancePackageName(String insurancePackage) throws ParseException {
        requireNonNull(insurancePackage);
        String trimmedInsurancePackage = insurancePackage.trim();
        if (!InsurancePackage.isValidInsurancePackage(trimmedInsurancePackage)) {
            throw new ParseException(InsurancePackage.MESSAGE_CONSTRAINTS);
        }
        return trimmedInsurancePackage;
    }

    /**
     * Parses a {@code String insurance package description}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseInsurancePackageDesc(String insurancePackageDesc) {
        requireNonNull(insurancePackageDesc);
        return insurancePackageDesc.trim();
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
     * Separates the priority from the tag entered.
     */
    public static Pair<String, Priority> parsePriority(String tag) {
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
    public static ArrayList<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final ArrayList<Tag> tagList = new ArrayList<>();
        for (String tagName : tags) {
            tagList.add(parseTag(tagName));
        }
        return tagList;
    }

    /**
     * Parses the arguments given for the find field into a List of String
     */
    public static List<String> parseFindKeywords(String input) {
        return Arrays.asList(input.split("\\s+"));
    }

    /**
     * Parses the arguments created in {@code ArgumentMultimap} to generate a List of
     * {@code FieldContainsKeywordsPredicate}
     */
    public static List<FieldContainsKeywordsPredicate> parseArgMap(ArgumentMultimap argMultimap) {
        List<FieldContainsKeywordsPredicate> predicatesList = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            List<String> nameKeywords = ParserUtil.parseFindKeywords((argMultimap.getValue(PREFIX_NAME).get()));
            NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
            predicatesList.add(namePredicate);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            List<String> phoneKeywords = ParserUtil.parseFindKeywords(argMultimap.getValue(PREFIX_PHONE).get());
            PhoneContainsKeywordsPredicate phonePredicate = new PhoneContainsKeywordsPredicate(phoneKeywords);
            predicatesList.add(phonePredicate);
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            List<String> emailKeywords = ParserUtil.parseFindKeywords(argMultimap.getValue(PREFIX_EMAIL).get());
            EmailContainsKeywordsPredicate emailPredicate = new EmailContainsKeywordsPredicate(emailKeywords);
            predicatesList.add(emailPredicate);
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            List<String> addressKeywords = ParserUtil.parseFindKeywords(argMultimap.getValue(PREFIX_ADDRESS).get());
            AddressContainsKeywordsPredicate addressPredicate = new AddressContainsKeywordsPredicate(addressKeywords);
            predicatesList.add(addressPredicate);
        }
        if (argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).isPresent()) {
            List<String> insurancePackageKeywords =
                    ParserUtil.parseFindKeywords(argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).get());
            InsurancePackageContainsKeywordsPredicate insurancePackagePredicate =
                    new InsurancePackageContainsKeywordsPredicate(insurancePackageKeywords);
            predicatesList.add(insurancePackagePredicate);
        }
        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            List<String> tagsKeywords =
                    ParserUtil.parseFindKeywords((String.join(" ", argMultimap.getAllValues(PREFIX_TAG))));
            TagsContainsKeywordsPredicate tagsPredicate = new TagsContainsKeywordsPredicate(tagsKeywords);
            predicatesList.add(tagsPredicate);
        }
        logger.info("predicatesList for FindCommand created");
        return predicatesList;
    }
}
