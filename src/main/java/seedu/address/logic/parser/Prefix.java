package seedu.address.logic.parser;

import java.io.Serializable;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix implements Serializable {
    private final String prefix;
    private final boolean isRequired;
    private final boolean isFlag;

    /**
     * Placeholder
     * @param prefix placeholder
     */
    public Prefix(String prefix) {
        this.prefix = prefix;
        this.isRequired = false;
        this.isFlag = false;
    }

    /**
     * Placeholder
     * @param prefix placeholder
     * @param isRequired placeholder
     */
    public Prefix(String prefix, boolean isRequired) {
        this.prefix = prefix;
        this.isRequired = isRequired;
        this.isFlag = false;
    }

    /**
     * Placeholder
     * @param prefix placeholder
     * @param isRequired placeholder
     * @param isFlag placeholder
     */
    public Prefix(String prefix, boolean isRequired, boolean isFlag) {
        this.prefix = prefix;
        this.isRequired = isRequired;
        this.isFlag = isFlag;
    }

    public boolean isFlag() {
        return isFlag;
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
