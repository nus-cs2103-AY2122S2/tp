package seedu.address.logic;

import seedu.address.commons.core.DataType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class FilterType {

    public static final String MESSAGE_CONSTRAINTS
            = "Invalid filter type for the data specified\nRefer to help for the list of filter types";

    public static final Map<DataType, HashSet<String>> validFilterTypes = loadFilterTypes();

    public final String filterType;

    /**
     * Constructs a {@code FilterType}.
     */
    public FilterType(DataType dataType, String filterType) {
        requireNonNull(dataType, filterType);
        checkArgument(isValidFilterType(dataType, filterType), MESSAGE_CONSTRAINTS);
        this.filterType = filterType;
    }

    /**
     * Returns a map of data type to a valid filter type.
     */
    public static Map<DataType, HashSet<String>> loadFilterTypes() {
        HashSet<String> applicantTypes = new HashSet<>();
        applicantTypes.add("name");

        HashMap<DataType, HashSet<String>> filterTypes = new HashMap<>();
        filterTypes.put(DataType.APPLICANT, applicantTypes);

        return filterTypes;
    }

    /**
     * Returns true if a given string is a valid filter type for the given data type.
     */
    public static boolean isValidFilterType(DataType dataType, String filterType) {
        return validFilterTypes.get(dataType).contains(filterType);
    }

    @Override
    public String toString() {
        return filterType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterType // instanceof handles nulls
                && filterType.equals(((FilterType) other).filterType)); // state check
    }

    @Override
    public int hashCode() {
        return filterType.hashCode();
    }
}
