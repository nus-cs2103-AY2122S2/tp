package seedu.address.model.application;

import static java.util.Objects.requireNonNull;

public class Details {

    //TODO finalize the default detail format
    public static final String DEFAULT_DETAIL = "To add details, use the edit command";

    public final String details;

    /**
     * Constructs a {@code Details}.
     */
    public Details() {
        details = DEFAULT_DETAIL;
    }

    /**
     * Constructs a {@code Details}.
     *
     * @param detailInput A non-empty string of detail.
     */
    public Details(String detailInput) {
        requireNonNull(detailInput);
        details = detailInput;
    }

    /**
     * Returns true if a given string is a placeholder for an empty detail.
     */
    public static boolean isNotSet(String test) {
        return test.equals(DEFAULT_DETAIL);
    }

    @Override
    public String toString() {
        return details;
    }
}
