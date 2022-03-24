package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

public abstract class Name {

    public final String fullName;

    /**
     * Constructs a {@code Name}
     *
     * @param name a valid name
     */
    protected Name(String name) {
        requireNonNull(name);
        this.fullName = name;
    }

    /**
     * Returns true if given {@code substring} is part of this {@code Name}.
     * This method is case-insensitive.
     *
     * @param substring Substring to match this Name against.
     * @return True if given substring is part of this Name.
     */
    public boolean containsIgnoreCase(String substring) {
        requireNonNull(substring);
        return fullName.toLowerCase().contains(substring.toLowerCase());
    }

    public String toString() {
        return fullName;
    }

    public int hashCode() {
        return fullName.hashCode();
    }


}
