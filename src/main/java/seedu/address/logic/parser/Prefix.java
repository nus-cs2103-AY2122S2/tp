package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final boolean isRequired;

    /**
     * Placeholder
     * @param prefix placeholder
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
        this.isRequired = false;
    }

    /**
     * Placeholder
     * @param prefix placeholder
     * @param isRequired placeholder
     */
    public Prefix(String prefix, boolean isRequired) {
        this.prefix = prefix;
        this.isRequired = isRequired;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

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
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
