package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PersonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Person name must consist of alphabets"
                    + "and it should not be blank";


    public static final String VALIDATION_REGEX = "^([a-zA-Z])+[a-zA-Z_ ]*";

    public final String personName;

    /**
     * Construct a {@code PersonName}
     *
     * @param personName to describe the expense.
     */
    public PersonName(String personName) {
        requireNonNull(personName);
        checkArgument(isValidPersonName(personName), MESSAGE_CONSTRAINTS);
        this.personName = personName;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidPersonName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return personName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonName // instanceof handles nulls
                && personName.equals(((PersonName) other).personName)); // state check
    }

    @Override
    public int hashCode() {
        return personName.hashCode();
    }
}
