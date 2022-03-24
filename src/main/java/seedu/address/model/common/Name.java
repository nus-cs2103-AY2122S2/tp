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
