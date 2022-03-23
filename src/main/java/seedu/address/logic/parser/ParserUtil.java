package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Pronoun;
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
     * Parses {@code String userInput} into an {@code Label}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the {@code trimmedLabel} is invalid.
     */
    public static Label parseLabel(String userInput, Object value) throws ParseException {
        requireNonNull(userInput);
        String trimmedUserInput = userInput.trim();
        if (trimmedUserInput.contains(" l/")) {
            String[] inputWithTag = trimmedUserInput.split(" l/", 2);
            String trimmedLabel = inputWithTag[1].trim();
            if (!Label.isValidLabel(trimmedLabel)) {
                throw new ParseException(Label.MESSAGE_CONSTRAINTS);
            }
            return new Label(trimmedLabel, false);
        } else {
            return new Label(String.valueOf(value.hashCode()), true);
        }
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
     * Parses a {@code String company} into a {@code Company}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code company} is invalid.
     */
    public static Company parseCompany(String company) throws ParseException {
        requireNonNull(company);
        String trimmedCompany = company.trim();
        if (!Company.isValidCompany(trimmedCompany)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Company(trimmedCompany);
    }

    /**
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code jobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
    }

    /**
     * Parses a {@code String Address} into a {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        String[] addressWithTag = trimmedAddress.split(" l/");
        //Regardless if there is a label or not, the first entry in the array
        //will always be the main value (without label), so this works
        if (!Address.isValidAddress(addressWithTag[0]) || addressWithTag[0].contains("l/")) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(addressWithTag[0].trim());
    }

    /**
     * Parses a collection of {@code Collection<String> addresses} into pairs of {@code Address} and its {@code Label}.
     * The Address object-label pairs are then added into a HashMap.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given address or label is invalid.
     */
    public static HashMap<Label, Address> parseAddresses(Collection<String> addresses) throws ParseException {
        requireNonNull(addresses);
        final HashMap<Label, Address> addressesMap = new HashMap<>();
        for (String address : addresses) {
            Address parsedAddress = parseAddress(address);
            Label label = parseLabel(address, parsedAddress);
            addressesMap.put(label, parsedAddress);
        }
        return addressesMap;
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
        String[] phoneWithTag = trimmedPhone.split(" l/");
        //Regardless if there is a label or not, the first entry in the array
        //will always be the main value (without label), so this works
        if (!Phone.isValidPhone(phoneWithTag[0]) || phoneWithTag[0].contains("l/")) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phoneWithTag[0].trim());
    }

    /**
     * Parses a collection of {@code Collection<String> numbers} into pairs of {@code Phone} and its {@code Label}.
     * The Phone object-label pairs are then added into a HashMap.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given phone or label is invalid.
     */
    public static HashMap<Label, Phone> parseNumbers(Collection<String> numbers) throws ParseException {
        requireNonNull(numbers);
        final HashMap<Label, Phone> numbersMap = new HashMap<>();
        for (String phone : numbers) {
            Phone parsedPhone = parsePhone(phone);
            Label label = parseLabel(phone, parsedPhone);
            numbersMap.put(label, parsedPhone);
        }
        return numbersMap;
    }

    /**
     * Parses a {@code String email} into a {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        String[] emailWithTag = trimmedEmail.split(" l/");
        //Regardless if there is a label or not, the first entry in the array
        //will always be the main value (without label), so this works
        if (!Email.isValidEmail(emailWithTag[0]) || emailWithTag[0].contains("l/")) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(emailWithTag[0].trim());
    }

    /**
     * Parses a collection of {@code Collection<String> emails} into pairs of {@code Email} and its {@code Label}.
     * The Email object-label pairs are then added into a HashMap.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given email or label is invalid.
     */
    public static HashMap<Label, Email> parseEmails(Collection<String> emails) throws ParseException {
        requireNonNull(emails);
        final HashMap<Label, Email> emailMap = new HashMap<>();
        for (String email : emails) {
            Email parsedEmail = parseEmail(email);
            Label label = parseLabel(email, parsedEmail);
            emailMap.put(label, parsedEmail);
        }
        return emailMap;
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
     * Parses a {@code String pronoun} into a {@code Pronoun}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code pronoun} is invalid.
     */
    public static Pronoun parsePronoun(String pronoun) throws ParseException {
        requireNonNull(pronoun);
        String trimmedPronoun = pronoun.trim();
        if (!Pronoun.isValidPronoun(trimmedPronoun)) {
            throw new ParseException(Pronoun.MESSAGE_CONSTRAINTS);
        }
        return new Pronoun(trimmedPronoun);
    }

    /**
     * Parses {@code Collection<String> pronouns} into a {@code Set<Pronoun>}.
     */
    public static Set<Pronoun> parsePronouns(Collection<String> pronouns) throws ParseException {
        requireNonNull(pronouns);
        final Set<Pronoun> pronounSet = new HashSet<>();
        for (String pronoun : pronouns) {
            pronounSet.add(parsePronoun(pronoun));
        }
        return pronounSet;
    }

    /**
     * Replaces all placeholder labels in the given {@code HashMap<Label, T>} with default labels.
     *
     * @param entries The {@code HashMap<Label, T>} of key-value pairs to be given default labels.
     * @param <T> The type of the value in the given {@code HashMap<Label, T>}.
     * @return A {@code HashMap<Label, T>} with default labels in place of placeholders.
     */
    public static <T> Map<Label, T> replacePlaceholdersInMap(Map<Label, T> entries) {
        //TreeMaps are implemented with trees so entries are sorted automatically
        Map<Label, T> labelledEntries = new TreeMap<>();
        Map<Label, T> placeholderEntries = new TreeMap<>();

        //Moves all labelled entries to labelledEntries and placeholder entries to placeholderEntries
        for (Label label : entries.keySet()) {
            if (label.isPlaceholder) {
                placeholderEntries.put(label, entries.get(label));
            } else {
                labelledEntries.put(label, entries.get(label));
            }
        }

        int i = labelledEntries.size() + 1;

        for (Label label : placeholderEntries.keySet()) {
            labelledEntries.put(new Label("#" + i, false), placeholderEntries.get(label));
            i++;
        }
        return labelledEntries;
    }
}
