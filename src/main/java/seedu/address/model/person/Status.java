package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Status implements Comparable<Status> {

    public static final String MESSAGE_CONSTRAINTS = "Status should be either 'blacklist' or 'favourite'";
    public final String value;

    /**
     * Constructor for a status
     * Represents a status for a Person.
     *
     * @param status
     */
    public Status(String status) {
        requireNonNull(status);
        String lowerCaseStatus = status.toLowerCase();
        checkArgument(isValidStatus(lowerCaseStatus), MESSAGE_CONSTRAINTS);
        // Here, we ensure that status is in lower case.
        value = lowerCaseStatus;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    public static boolean isValidStatus(String test) {
        return (test.equals("blacklist") || test.equals("favourite") || test.equals(""));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Status status) {
        return value.compareTo(status.value);
    }
}
