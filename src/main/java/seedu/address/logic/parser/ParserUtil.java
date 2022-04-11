package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MAX_INPUT_LIMIT;
import static seedu.address.commons.core.Messages.MESSAGE_MAX_SIZE_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.order.Details.ITEM_MESSAGE_LIMIT;
import static seedu.address.model.order.Details.ITEM_SIZE_LIMIT;
import static seedu.address.model.order.Details.QUANTITY_MESSAGE_LIMIT;
import static seedu.address.model.order.Details.QUANTITY_SIZE_MAX_LIMIT;
import static seedu.address.model.order.Details.QUANTITY_SIZE_MIN_LIMIT;
import static seedu.address.model.person.Address.ADDRESS_SIZE_MAX_LIMIT;
import static seedu.address.model.person.Address.ADDRESS_SIZE_MIN_LIMIT;
import static seedu.address.model.person.Address.MESSAGE_ADDRESS_LIMIT;
import static seedu.address.model.person.Email.EMAIL_SIZE_MAX_LIMIT;
import static seedu.address.model.person.Email.EMAIL_SIZE_MIN_LIMIT;
import static seedu.address.model.person.Email.MESSAGE_EMAIL_LIMIT;
import static seedu.address.model.person.Name.MESSAGE_NAME_LIMIT;
import static seedu.address.model.person.Name.NAME_SIZE_MAX_LIMIT;
import static seedu.address.model.person.Name.NAME_SIZE_MIN_LIMIT;
import static seedu.address.model.person.Phone.MESSAGE_PHONE_LIMIT;
import static seedu.address.model.person.Phone.PHONE_SIZE_MAX_LIMIT;
import static seedu.address.model.person.Phone.PHONE_SIZE_MIN_LIMIT;
import static seedu.address.model.tag.Tag.MESSAGE_TAG_LIMIT;
import static seedu.address.model.tag.Tag.TAG_SIZE_MAX_LIMIT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DateChecker;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
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
        int trimmedNameLength = trimmedName.length();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        } else if (trimmedNameLength < NAME_SIZE_MIN_LIMIT || trimmedNameLength > NAME_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_NAME_LIMIT, PREFIX_NAME));
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
        int trimmedPhoneLength = trimmedPhone.length();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        } else if (trimmedPhoneLength < PHONE_SIZE_MIN_LIMIT || trimmedPhoneLength > PHONE_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_PHONE_LIMIT, PREFIX_PHONE));
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
        int trimmedAddressLength = trimmedAddress.length();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        } else if (trimmedAddressLength < ADDRESS_SIZE_MIN_LIMIT || trimmedAddressLength > ADDRESS_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_ADDRESS_LIMIT, PREFIX_ADDRESS));
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String remark} into an {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
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
        int trimmedEmailLength = trimmedEmail.length();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        } else if (trimmedEmailLength < EMAIL_SIZE_MIN_LIMIT || trimmedEmailLength > EMAIL_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_EMAIL_LIMIT, PREFIX_EMAIL));
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses {@code Collection<String> detail} into a {@code List<Detail>}.
     */
    public static List<Details> parseDetails(Collection<String> details) throws ParseException {
        requireNonNull(details);
        if (details.size() > Order.MAX_DETAIL_SIZE) {
            throw new ParseException(String.format(MESSAGE_MAX_SIZE_LIMIT, PREFIX_DETAILS, Order.MAX_DETAIL_SIZE));
        }

        final List<Details> detailsList = new ArrayList<>();
        for (String detailInput : details) {
            detailsList.add(parseDetail(detailInput));
        }
        return detailsList;
    }

    /**
     * Parses a {@code String detail} into a {@code Details}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code detail} is invalid.
     */
    public static Details parseDetail(String detail) throws ParseException {
        requireNonNull(detail);
        String trimmedDetails = detail.trim();
        if (!Details.isValidDetails(trimmedDetails)) {
            throw new ParseException(Details.MESSAGE_CONSTRAINTS);
        }

        // parse and validate input quantity
        String parseInputQuantity = Details.parseValidatedQuantity(trimmedDetails);
        int inputQuantity = Integer.parseInt(parseInputQuantity);
        assert inputQuantity >= 0; // negative numbers must be rejected in regex case
        if (inputQuantity < QUANTITY_SIZE_MIN_LIMIT || inputQuantity > QUANTITY_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_MAX_INPUT_LIMIT, PREFIX_DETAILS,
                    QUANTITY_MESSAGE_LIMIT));
        }

        String parseInputItem = Details.parseValidatedItem(trimmedDetails);
        if (parseInputItem.length() > ITEM_SIZE_LIMIT) {
            throw new ParseException(String.format(MESSAGE_MAX_INPUT_LIMIT, PREFIX_DETAILS,
                    ITEM_MESSAGE_LIMIT));
        }
        return new Details(trimmedDetails, parseInputItem, inputQuantity);
    }

    /**
     * Parses a {@code String deliveryDateTime} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deliveryDateTime} is invalid.
     *
     */
    public static DeliveryDateTime parseDeliveryDateTime(String deliveryDateTime) throws ParseException {
        requireNonNull(deliveryDateTime);
        String trimmedDeliveryDateTime = deliveryDateTime.trim();
        String parsedDeliveryDateTime = DateChecker.parsePotentialNaturalDate(trimmedDeliveryDateTime);
        if (!DeliveryDateTime.isValidDeliveryDateTime(parsedDeliveryDateTime)) {
            throw new ParseException(DeliveryDateTime.MESSAGE_CONSTRAINTS);
        } else if (!DeliveryDateTime.isValidLeapYearDeliveryDateTimeValue(parsedDeliveryDateTime)) {
            throw new ParseException(DeliveryDateTime.MESSAGE_CONSTRAINTS_LEAP_YEAR);
        }
        return new DeliveryDateTime(parsedDeliveryDateTime);
    }

    /**
     * Parses a {@code String collectionType} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code collectionType} is invalid.
     *
     */
    public static CollectionType parseCollectionType(String collectionType) throws ParseException {
        requireNonNull(collectionType);
        String trimmedCollectionType = collectionType.trim();
        String trimmedCollectionTypeCapital = trimmedCollectionType.substring(0, 1).toUpperCase()
                + trimmedCollectionType.substring(1).toLowerCase(); // Make it Capitalised
        if (!CollectionType.isValidCollectionTypeValue(trimmedCollectionTypeCapital)) {
            throw new ParseException(CollectionType.MESSAGE_CONSTRAINTS);
        }
        if (trimmedCollectionTypeCapital.equals(CollectionType.DELIVERY.getValue())) {
            return CollectionType.DELIVERY;
        } else if (trimmedCollectionTypeCapital.equals(CollectionType.PICKUP.getValue())) {
            return CollectionType.PICKUP;
        } else {
            throw new ParseException(CollectionType.MESSAGE_CONSTRAINTS);
        }
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
        } else if (trimmedTag.length() > TAG_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(MESSAGE_TAG_LIMIT, PREFIX_TAG));
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
     * Returns true if only {@code searchPrefix} is the only prefix in {@code ArgumentMultimap}.
     * Checks ignore all other prefixes not passed in initial {@code tokenize()}  method.
     */
    public static boolean isOnlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix searchPrefix) {
        ArrayList<Prefix> argPrefixes = argumentMultimap.getAllPrefixes();
        // check that prefix exists
        boolean isSearchPrefixExists = argPrefixes.stream().filter(prefix -> prefix.equals(searchPrefix)).count() == 1;

        // check no other prefix exists except for blank prefix as by-product of tokenizing
        boolean noOtherPrefixExists = argPrefixes.stream()
                .filter(prefix -> !prefix.equals(searchPrefix) || prefix.equals(PREFIX_BLANK))
                .count() == 1;
        return isSearchPrefixExists && noOtherPrefixExists;
    }
}
