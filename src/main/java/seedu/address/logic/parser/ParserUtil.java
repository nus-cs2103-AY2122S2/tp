package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;
import seedu.address.model.userimage.FilePath;
import seedu.address.model.userimage.UserImage;

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
     * Parses a {@code String region} into an {@code Region}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code region} is invalid.
     */
    public static Region parseRegion(String region) throws ParseException {
        requireNonNull(region);
        String trimmedRegion = region.trim();
        if (!Region.isValidRegion(trimmedRegion)) {
            throw new ParseException(Region.MESSAGE_CONSTRAINTS);
        }
        return Region.fromString(trimmedRegion);
    }

    /**
     * Parses a {@code String size} into an {@code Size}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code size} is invalid.
     */
    public static Size parseSize(String size) throws ParseException {
        requireNonNull(size);
        String trimmedSize = size.trim();
        if (!Size.isValidSize(trimmedSize)) {
            throw new ParseException(Size.MESSAGE_CONSTRAINTS);
        }
        return Size.fromString(trimmedSize);
    }

    /**
     * Parses a {@code String price} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
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

        Region region = parseRegion(propertySplit[0]);
        Address address = parseAddress(propertySplit[1]);
        Size size = parseSize(propertySplit[2]);
        Price price = parsePrice(propertySplit[3]);
        return new Property(region, address, size, price);
    }

    /**
     * Parses {@code Collection<String> properties} into a {@code Set<Property>}.
     */
    public static Set<Property> parseProperties(Collection<String> properties) throws ParseException {
        requireNonNull(properties);
        Set<Property> propertySet = new HashSet<>();
        for (String property : properties) {
            propertySet.add(parseProperty(property));
        }
        return propertySet;
    }

    /**
     * Parses a {@code String preference} into an {@code Preference}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code preference} is invalid.
     */
    public static Preference parsePreference(String preference) throws ParseException {
        requireNonNull(preference);
        String trimmedPreference = preference.trim();
        String[] preferenceSplit = trimmedPreference.split(",");

        if (preferenceSplit.length != 4) {
            throw new ParseException(Preference.MESSAGE_CONSTRAINTS);
        }

        Region region = parseRegion(preferenceSplit[0]);
        Size size = parseSize(preferenceSplit[1]);
        Price lowPrice = parsePrice(preferenceSplit[2]);
        Price highPrice = parsePrice(preferenceSplit[3]);
        return new Preference(region, size, lowPrice, highPrice);
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

    /**
     * Parses a {@code Collection<String> userImage} into a {@code Set<UserImage>}.
     */
    public static Set<UserImage> parseUserImages(Collection<String> userImages) throws ParseException {
        requireNonNull(userImages);
        Set<UserImage> userImageSet = new LinkedHashSet<>();
        for (String userImage : userImages) {
            userImageSet.add(parseUserImage(userImage));
        }
        return userImageSet;
    }

    /**
     * Parses a {@code String userImage} into a {@code UserImage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code userImage} is invalid.
     */
    public static UserImage parseUserImage(String userImage) throws ParseException {
        String trimmedUserImage = userImage.trim();
        String[] splitUserImage = trimmedUserImage.split(":");

        FilePath filePath = parseFilePath(splitUserImage[0]);
        String description = null;
        if (splitUserImage.length == 2) {
            description = parseDescription(splitUserImage[1]);
        }
        return new UserImage(filePath, description);
    }

    /**
     * Parses a {@code String filePath} into a {@code FilePath}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code filePath} is invalid.
     */
    public static FilePath parseFilePath(String filePath) throws ParseException {
        requireNonNull(filePath);
        String trimmedFilePath = filePath.trim();
        if (!FilePath.isValidFilePath(trimmedFilePath)) {
            throw new ParseException(FilePath.MESSAGE_CONSTRAINTS);
        }
        return new FilePath(trimmedFilePath);
    }

    /**
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return trimmedDescription;
    }
}
