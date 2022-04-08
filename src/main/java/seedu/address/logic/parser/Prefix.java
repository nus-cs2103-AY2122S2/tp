package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 'm/' in 'add James m/CS2106'.
 */
public class Prefix {
    private final String prefix;
    private final String description;

    /**
     * Constructs a Prefix.
     * @param prefix the prefix string.
     * @param desc the description of the prefix.
     */
    public Prefix(String prefix, String desc) {
        this.description = desc;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix())
                && otherPrefix.getDescription().equals(getDescription());
    }
}
