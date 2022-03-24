package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's salary in the HustleBook.
 */
public class Salary {

    public static final String MESSAGE_CONSTRAINTS = "Salary should only contain numbers";
    private static final String VALIDATION_REGEX = "\\d+";
    public final String value;

    /**
     * Constructs a {@code Salary}.
     *
     * @param salary A valid salary value.
     */
    public Salary(String salary) {
        requireNonNull(salary);
        checkArgument(isValidSalary(salary), MESSAGE_CONSTRAINTS);
        this.value = salary;
    }

    /**
     * Constructs a {@code Salary} with a default value of "0".
     */
    public Salary() {
        this.value = "0";
    }

    /**
     * Returns true if the given string is a valid salary value.
     *
     * @param test The string used to check if it is a valid salary value.
     * @return A boolean stating if the string is valid or not.
     */
    public static boolean isValidSalary(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        boolean isSameObj = other == this;
        boolean isCorrectObj = other instanceof Salary;
        boolean isCorrectState = false;
        if (isCorrectObj) {
            Salary tempSalaryObj = (Salary) other;
            isCorrectState = value.equals(tempSalaryObj.value);
        }
        return isSameObj || (isCorrectObj && isCorrectState);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
