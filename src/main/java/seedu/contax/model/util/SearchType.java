package seedu.contax.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Utility of search type.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)} (String)}.
 */
public class SearchType {

    public static final String SEARCH_TYPE_CONSTRAINTS =
            "Search type should only be name, address, phone, or email";

    public static final String TYPE_NAME = "name";
    public static final String TYPE_ADDRESS = "address";
    public static final String TYPE_PHONE = "phone";
    public static final String TYPE_EMAIL = "email";

    public static final String[] VALIDATION_TYPE = {TYPE_NAME, TYPE_ADDRESS, TYPE_PHONE, TYPE_EMAIL};

    public final String searchType;

    /**
     * Constructs a {@code Name}.
     *
     * @param type A valid search type.
     */
    public SearchType(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type.toLowerCase()), SEARCH_TYPE_CONSTRAINTS);
        searchType = type.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidType(String test) {
        requireNonNull(test);
        return Arrays.asList(VALIDATION_TYPE).contains(test.toLowerCase());
    }

    @Override
    public String toString() {
        return this.searchType;
    }

    @Override
    public int hashCode() {
        return this.searchType.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof SearchType)) {
            return false;
        }

        return ((SearchType) o).searchType.equals(this.searchType);
    }

}
