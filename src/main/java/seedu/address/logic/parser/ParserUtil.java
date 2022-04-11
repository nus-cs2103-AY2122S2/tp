package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;

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
        int trimNameLen = trimmedName.length();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        } else if (trimNameLen > Name.NAME_SIZE_MAX_LIMIT || trimNameLen < Name.NAME_SIZE_MIN_LIMIT) {
            throw new ParseException(String.format(Name.MESSAGE_NAME_LIMIT));
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
        } else if (trimmedAddress.length() > Address.ADDRESS_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(Address.MESSAGE_ADDRESS_LIMIT));
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
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String status} into an {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Status} is invalid.
     */
    public static ClassCode parseClassCode(String classCode) throws ParseException {
        requireNonNull(classCode);
        String trimmedClassCode = classCode.trim();
        if (!ClassCode.isValidClassCode(trimmedClassCode)) {
            throw new ParseException(ClassCode.MESSAGE_CONSTRAINTS);
        }
        return new ClassCode(trimmedClassCode);
    }

    /**
     * Parses a {@code String activity} into a {@code Activity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code activity} is invalid.
     */
    public static Activity parseActivity(String activity) throws ParseException {
        requireNonNull(activity);
        String trimmedActivity = activity.trim();
        if (!Activity.isValidActivityName(trimmedActivity)) {
            throw new ParseException(Activity.MESSAGE_CONSTRAINTS);
        } else if (trimmedActivity.length() > Activity.ACTIVITY_SIZE_MAX_LIMIT) {
            throw new ParseException(String.format(Activity.MESSAGE_ACTIVITY_LIMIT));
        }
        return new Activity(trimmedActivity);
    }

    /**
     * Parses {@code Collection<String> activities} into a {@code Set<Activity>}.
     */
    public static Set<Activity> parseActivities(Collection<String> activities) throws ParseException {
        requireNonNull(activities);
        final Set<Activity> activitySet = new HashSet<>();
        for (String activityName : activities) {
            activitySet.add(parseActivity(activityName));
        }
        return activitySet;
    }
}
