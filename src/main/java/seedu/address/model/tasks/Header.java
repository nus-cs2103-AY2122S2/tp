package seedu.address.model.tasks;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a task's header in the taskList.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeader(String)}
 */
public class Header {

    public static final String MESSAGE_CONSTRAINTS = "Header should only contain alphanumeric "
            + "characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullHeader;

    /**
     * Constructs a {@code Header}.
     * @param header A valid header.
     */
    public Header(String header) {
        requireNonNull(header);
        checkArgument(isValidHeader(header), MESSAGE_CONSTRAINTS);
        fullHeader = header;
    }

    /**
     * Returns true if a given string is a valid header.
     */
    public static boolean isValidHeader(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean contains(String s) {
        return fullHeader.toLowerCase().contains(s.toLowerCase());
    }

    @Override
    public String toString() {
        return fullHeader;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Header) {
            String currentHeader = fullHeader.toLowerCase();
            String otherHeader = ((Header) other).fullHeader.toLowerCase();
            if (currentHeader.contains(" ") && otherHeader.contains(" ")) {
                return currentHeader.replaceAll("\\s", "")
                        .equals(otherHeader.replaceAll("\\s", ""));
            } else {
                return currentHeader.equals(otherHeader);
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return fullHeader.hashCode();
    }

}

