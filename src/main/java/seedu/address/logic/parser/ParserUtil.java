package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

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
     * Parses a {@code String property} into an {@code Property}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code property} is invalid.
     */
    public static Property parseProperty(String property) throws ParseException {
        requireNonNull(property);
        String trimmedProperty = property.trim();
        String[] propertySplit = trimmedProperty.split(",");

        if (propertySplit.length != 4) {
            throw new ParseException(Property.MESSAGE_CONSTRAINTS);
        }

        if (!Region.isValidRegion(propertySplit[0].trim())) {
            throw new ParseException(Region.MESSAGE_CONSTRAINTS);
        }

        if (!Address.isValidAddress(propertySplit[1].trim())) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }

        if (!Size.isValidSize(propertySplit[2].trim())) {
            throw new ParseException(Size.MESSAGE_CONSTRAINTS);
        }

        if (!Price.isValidPrice(propertySplit[3].trim())) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }

        Region region = Region.fromString(propertySplit[0].trim());
        Address address = new Address(propertySplit[1].trim());
        Size size = Size.fromString(propertySplit[2].trim());
        Price price = new Price(propertySplit[3].trim());
        return new Property(region, address, size, price);
    }

    /**
     * Parses a {@code String userType} into a {@code UserType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code userType} is invalid.
     */
    public static UserType parseUserType(String userType) throws ParseException {
        requireNonNull(userType);
        String trimmedUserType = userType.trim();
        if (!UserType.isValidUserType(trimmedUserType)) {
            throw new ParseException(UserType.MESSAGE_CONSTRAINTS);
        }
        return new UserType(trimmedUserType);
    }

}
