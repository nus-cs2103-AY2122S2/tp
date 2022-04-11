package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a mod a Person's is taking.
 * Guarantees: immutable; is valid as declared in {@link #isValidMod(String)}
 */
public class Mod {

    public static final String MESSAGE_CONSTRAINTS = "Mod codes must be in the form of 2-3 Capitalised characters, 4 "
            + "digits then an optional extra capitalised character.";

    public static final String DEFAULT_GROUP_MESSAGE_CONSTRAINTS = "Default group titles within a group must not be "
            + "blank";

    /*
     * The first 2-3 characters of the module code must be capital letters,
     * followed by 4 digits, and an optional capital letter.
     */
    public static final String MOD_VALIDATION_REGEX = "^[A-Z]{2,3}[0-9]{4}[A-Z]?";

    public static final String GROUP_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    private String defaultGroup;

    /**
     * Constructs an {@code Mod}.
     *
     * @param mod A valid mod.
     */
    public Mod(String mod) {
        requireNonNull(mod);
        checkArgument(isValidMod(mod), MESSAGE_CONSTRAINTS);
        this.value = mod;
        this.defaultGroup = null;
    }

    /**
     * Constructor for the {@code Mod} class.
     * @param mod The module.
     * @param defaultGrp The default group.
     */
    public Mod(String mod, String defaultGrp) {
        requireNonNull(mod);
        requireNonNull(defaultGrp);
        checkArgument(isValidDefaultGroup(defaultGrp), DEFAULT_GROUP_MESSAGE_CONSTRAINTS);
        value = mod;
        this.defaultGroup = defaultGrp;
    }

    /**
     * Returns true if a given string is a valid mod.
     */
    public static boolean isValidMod(String test) {
        return test.matches(MOD_VALIDATION_REGEX);
    }

    public static boolean isValidDefaultGroup(String test) {
        return test.matches(GROUP_VALIDATION_REGEX);
    }

    public String getDefaultGroup() {
        return defaultGroup;
    }

    public String getMod() {
        return this.value;
    }

    public void setDefaultGroup(String defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Mod // instanceof handles nulls
                && value.equals(((Mod) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
