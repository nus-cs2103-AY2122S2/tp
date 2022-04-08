package seedu.address.model.common;

import static java.util.Objects.requireNonNull;

public abstract class Name implements Comparable<Name> {

    public final String fullName;

    /**
     * Constructs a {@code Name}
     *
     * @param name a valid name
     */
    public Name(String name) {
        requireNonNull(name);
        this.fullName = name;
    }

    /**
     * Returns true if given {@code nameSubstring} is part of this {@code Name}.
     * This method is case-insensitive.
     *
     * @param nameSubstring Name substring to match this Name against.
     * @return True if given name substring is part of this Name.
     */
    public boolean containsIgnoreCase(Name nameSubstring) {
        requireNonNull(nameSubstring);
        return fullName.toLowerCase().contains(nameSubstring.fullName.toLowerCase());
    }

    public String toString() {
        return fullName;
    }

    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public int compareTo(Name name) {
        return this.fullName.compareTo(name.fullName);
    }
}
