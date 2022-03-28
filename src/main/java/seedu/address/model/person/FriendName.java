package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.common.Name;



/**
 * Represents a Friend's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFriendName(String)}
 */
public class FriendName extends Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z][\\p{Alnum} ]*";

    /**
     * Constructs a {@code FriendName}.
     *
     * @param name A valid name.
     */
    public FriendName(String name) {
        super(name);
        checkArgument(isValidFriendName(name), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidFriendName(String test) {
        // Ensure in implementation Regex is not null
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendName // instanceof handles nulls
                && fullName
                .equalsIgnoreCase(((FriendName) other).fullName));
    }
}
