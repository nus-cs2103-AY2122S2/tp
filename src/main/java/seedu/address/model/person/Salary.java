package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a person's salary in the HustleBook.
 */
public class Salary {

    public static final String MESSAGE_CONSTRAINTS = "Salary should only contain numbers and at most 15 digits";
    public static final String DEFAULT_VALUE = "0";
    private static final String VALIDATION_REGEX = "\\d{1,15}";
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
        this.value = DEFAULT_VALUE;
    }

    /**
     * Returns true if the given string is a valid salary value.
     *
     * @param test The string used to check if it is a valid salary value.
     * @return A boolean stating if the string is valid or not.
     */
    public static boolean isValidSalary(String test) {
        return (test.matches(VALIDATION_REGEX));
    }

    /**
     * Method to compare Salary with other Salary.
     * Returns 0 if Salary is equal, -1 if this Salary is lesser and 1 if it is higher.
     *
     * @param otherSalary Another Salary to compare to.
     * @return Integer indicating if Salary is equal, less or more than otherSalary
     */
    public int compare(Salary otherSalary) {
        return -1 * this.value.compareTo(otherSalary.value);
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
