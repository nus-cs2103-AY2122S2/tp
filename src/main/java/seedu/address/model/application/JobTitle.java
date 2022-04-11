package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the job title of an Application in InternApply.
 */
public class JobTitle {

    public static final String MESSAGE_CONSTRAINTS =
            "Job Title should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the job title must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code JobTitle}.
     *
     * @param jobTitle The job title.
     */
    public JobTitle(String jobTitle) {
        requireNonNull(jobTitle);
        checkArgument(isValidJobTitle(jobTitle), MESSAGE_CONSTRAINTS);
        this.value = jobTitle;
    }

    /**
     * Returns true if a given string is a valid job title.
     */
    public static boolean isValidJobTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobTitle // instanceof handles nulls
                && value.equals(((JobTitle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
