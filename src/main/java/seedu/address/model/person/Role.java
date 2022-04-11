package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's role in the contact list.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {


    public static final String MESSAGE_CONSTRAINTS = "Role should not be blank "
            + "and should only have the following values\n"
            + "1. Professor\n"
            + "2. Tutor\n"
            + "3. Lecturer\n"
            + "4. TA\n"
            + "3. Researcher\n"
            + "5. Admin\n"
            + "6. Other";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z]+$";
    public final String value;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);

        if (role.equalsIgnoreCase("TA")) {
            value = role.toUpperCase();
        } else {
            value = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
        }
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX)
                && (test.equalsIgnoreCase("Professor")
                || test.equalsIgnoreCase("Tutor")
                || test.equalsIgnoreCase("Lecturer")
                || test.equalsIgnoreCase("TA")
                || test.equalsIgnoreCase("Researcher")
                || test.equalsIgnoreCase("Admin")
                || test.equalsIgnoreCase("Others"));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && value.equals(((Role) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
