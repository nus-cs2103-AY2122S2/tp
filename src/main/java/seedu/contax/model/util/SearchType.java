package seedu.contax.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Utility of search type.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)} (String)}
 */
public class SearchType {

    public static final String SEARCH_TYPE_CONSTRAINTS =
            "Search type should only be name, address, phone, or email";

    public static final String[] VALIDATION_TYPE = {"name", "address", "phone", "email"};

    public final String searchType;

    /**
     * Constructs a {@code Name}.
     *
     * @param type A valid search type.
     */
    public SearchType(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), SEARCH_TYPE_CONSTRAINTS);
        searchType = type;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidType(String test) {
        requireNonNull(test);
        return Arrays.asList(VALIDATION_TYPE).contains(test);
    }

}
