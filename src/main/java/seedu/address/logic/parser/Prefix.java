package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final String prefixFor;

    /**
     * Constructor for the Prefix class.
     * @param prefix the prefix of an argument
     * @param prefixFor the argument that the prefix is for
     */
    public Prefix(String prefix, String prefixFor) {
        this.prefix = prefix;
        this.prefixFor = prefixFor;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPrefixFor() {
        return prefixFor;
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
        return otherPrefix.getPrefix().equals(getPrefix()) && otherPrefix.getPrefixFor().equals(getPrefixFor());
    }
}
