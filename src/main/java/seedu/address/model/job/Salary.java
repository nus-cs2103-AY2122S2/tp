package seedu.address.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Salary {
    public static final String MESSAGE_CONSTRAINTS = "Salary must be a string with the format "
            + "[Non-negative integers][-][Non-negative integers]\n"
            + "The right bound salary should be equal to or more than the left bound salary\n"
            + "The maximum number of digits for the salary is 9 digits \n"
            + "The salary cannot start with a 0 eg 01234 - 0032134\n"
            + "Salary is salary paid per month and in SGD\n"
            + "eg 4000-5000";

    public static final String LEFT_REGEX = "^[ ]*[1-9][0-9]{0,8}+[ ]*";
    public static final String MIDDLE_REGEX = "[-]";
    public static final String RIGHT_REGEX = "[ ]*[1-9][0-9]{0,8}+[ ]*";
    public static final String VALIDATION_REGEX = LEFT_REGEX + MIDDLE_REGEX + RIGHT_REGEX + "$";

    public final String startingSalary;
    public final String endSalary;
    public final String salaryRange;

    /**
     * Constructs a salary object
     * @param startingSalary The left bound of salary.
     * @param endSalary The right bound of salary.
     */
    public Salary(String startingSalary, String endSalary) {
        requireNonNull(startingSalary, endSalary);
        this.salaryRange = startingSalary + "-" + endSalary;
        checkArgument(isValidSalary(startingSalary, endSalary, salaryRange), MESSAGE_CONSTRAINTS);
        this.startingSalary = startingSalary;
        this.endSalary = endSalary;
    }

    /**
     * Constructs a salary object with the salary range
     * @param salary salary range
     */
    public Salary(String salary) {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        int index = trimmedSalary.indexOf("-");
        this.startingSalary = trimmedSalary.substring(0, index).trim();
        this.endSalary = trimmedSalary.substring(index + 1).trim();
        this.salaryRange = startingSalary + "-" + endSalary;
    }

    /**
     * Checks if the Salary is valid.
     * @param startingSalary The left bound of salary.
     * @param endSalary The right bound of salary.
     * @param salaryRange The salary range.
     * @return True if the salary range is a valid format and end salary is more than or equal to the starting salary
     *          and false otherwise.
     */
    public static boolean isValidSalary(String startingSalary, String endSalary, String salaryRange) {
        return salaryRange.matches(VALIDATION_REGEX)
                && Integer.parseInt(startingSalary) <= Integer.parseInt(endSalary);
    }

    /**
     * checks for valid salary range
     * @param salary salary range
     * @return True if salary range is valid, false otherwise
     */
    public static boolean isValidSalary(String salary) {
        requireNonNull(salary);
        if (!salary.matches(VALIDATION_REGEX)) {
            return false;
        }
        String trimmedSalary = salary.trim();
        int index = trimmedSalary.indexOf("-");
        String startingSalary = trimmedSalary.substring(0, index).trim();
        String endSalary = trimmedSalary.substring(index + 1).trim();
        String salaryRange = startingSalary + "-" + endSalary;
        return salaryRange.matches(VALIDATION_REGEX)
                && Integer.parseInt(startingSalary) <= Integer.parseInt(endSalary);
    }

    @Override
    public String toString() {
        return "S$" + startingSalary + " - " + "S$" + endSalary;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.job.Salary
                && this.salaryRange.equals(((seedu.address.model.job.Salary) other).salaryRange));
    }

    @Override
    public int hashCode() {
        return salaryRange.hashCode();
    }
}
