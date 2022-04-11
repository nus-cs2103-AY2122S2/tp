package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Applicant's Id in the address book.
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS = "Id can take any positive integer value, "
            + "and it should not be blank";

    public final Integer uid;

    /**
     * Constructs an Id object.
     *
     * @param id A valid Id int
     */
    public Id(Integer id) {
        requireNonNull(id);
        checkArgument(isValidId(id.toString()), MESSAGE_CONSTRAINTS);
        uid = id;
    }

    /**
     * Returns true if the input integer is a valid Id.
     */
    public static boolean isValidId(String test) {
        return Integer.parseInt(test) > 0;
    }

    @Override
    public String toString() {
        return uid.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.Id
                && uid.equals(((seedu.address.model.applicant.Id) other).uid));
    }
}
