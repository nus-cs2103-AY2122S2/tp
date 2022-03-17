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

    public String toString() {
        return fullName;
    }

    public int hashCode() {
        return fullName.hashCode();
    }


}
