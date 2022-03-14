package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Event's Platform in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPlatform(String)}
 */
public class Platform {

    public static final String MESSAGE_CONSTRAINTS =
            "TODO CONSTRAINTS";

    /*
     * TODO VALIDATION REGEX
     */
//    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String platform;

    /**
     * Constructs a {@code Platform}.
     *
     * @param platform A valid platform.
     */
    public Platform(String platform) {
        requireNonNull(platform);
        checkArgument(isValidPlatform(platform), MESSAGE_CONSTRAINTS);
        this.platform = platform;
    }

    /**
     * Returns true if a given string is a valid platform.
     * TODO
     */
    public static boolean isValidPlatform(String test) {
        //return test.matches(VALIDATION_REGEX);
        return true;
    }


    @Override
    public String toString() {
        return platform;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && platform.equals(((Platform) other).platform)); // state check
    }

    @Override
    public int hashCode() {
        return platform.hashCode();
    }

}
