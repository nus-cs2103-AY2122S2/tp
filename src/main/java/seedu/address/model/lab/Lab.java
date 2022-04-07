package seedu.address.model.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.lab.exceptions.DuplicateLabException;
import seedu.address.model.lab.exceptions.InvalidLabStatusException;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents a Lab entry.
 * Guarantees: immutable; is valid as declared in {@link #isValidLab(String)}
 */
public class Lab {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab number should be an integer between 0 and 20 inclusive.";

    /*
     * Lab number has to contain only digits.
     */
    public static final String VALIDATION_REGEX = "\\d*";

    public final int labNumber;

    public final LabStatus labStatus;

    public final LabMark labMark;

    /**
     * Constructs an {@code Lab}.
     *
     * @param labNumber A valid lab number.
     */
    public Lab(String labNumber) {
        // labStatus is always initialized to {@code LabStatus.UNSUBMITTED}
        this(labNumber, LabStatus.UNSUBMITTED);
    }

    /**
     * Constructs an {@code Lab} with uninitialized {@code labMark}.
     *
     * @param labNumber A valid lab number.
     * @param labStatus The status of the Lab to be created.
     */
    private Lab(String labNumber, LabStatus labStatus) {
        this(labNumber, labStatus, new LabMark());
    }

    /**
     * Constructs a graded {@code Lab} with initialized {@code labMark}.
     *
     * @param labNumber A valid lab number.
     * @param labMark The score given to this Lab.
     */
    private Lab(String labNumber, LabMark labMark) {
        this(labNumber, LabStatus.GRADED, labMark);
    }

    /**
     * Constructs an {@code Lab} with initialized {@code labMark}.
     *
     * @param labNumber A valid lab number.
     * @param labStatus The status of the Lab to be created.
     * @param labMark The score given to this Lab.
     */
    private Lab(String labNumber, LabStatus labStatus, LabMark labMark) {
        requireAllNonNull(labNumber, labMark);
        checkArgument(isValidLab(labNumber), MESSAGE_CONSTRAINTS);
        this.labNumber = Integer.parseInt(labNumber);
        this.labStatus = labStatus;
        this.labMark = labMark;
    }

    /**
     * Returns true if a given string contains only digits and the Integer it parses to is between 0 and 20 inclusive.
     *
     * @param test The string to be checked and parsed.
     * @return True if the string is a valid Integer between 0 and 20 inclusive and false otherwise.
     */
    public static boolean isValidLab(String test) {
        return isValidString(test) && isIntegerInBounds(test);
    }

    private static boolean isValidString(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private static boolean isIntegerInBounds(String test) {
        try {
            int labNum = Integer.parseInt(test);
            return labNum >= 0 && labNum <= 20;
        } catch (NumberFormatException e) {
            // can't be parsed to Integer so return false.
            return false;
        }
    }

    /**
     * Returns a new immutable lab with the same attributes as {@code this}.
     */
    public Lab createCopy() {
        return new Lab(String.valueOf(labNumber), labStatus, labMark);
    }

    /**
     * Returns a new immutable Lab with the same lab number as {@code this} and the given lab status.
     * The {@code LabMark} of the returned Lab is left uninitialized.
     *
     * @throws DuplicateLabException If the given {@code LabStatus} is equal to the existing {@code LabStatus}.
     * @throws InvalidLabStatusException If the given {@code LabStatus} is equal to LabStatus.GRADED.
     */
    public Lab editLabStatus(LabStatus status) throws DuplicateLabException, InvalidLabStatusException {
        if (status.equals(this.labStatus)) {
            throw new DuplicateLabException();
        } else if (status.equals(LabStatus.GRADED)) {
            throw new InvalidLabStatusException();
        }
        return new Lab(String.valueOf(labNumber), status);
    }

    /**
     * Returns a new immutable Lab with the same lab number as {@code this}, a status of "GRADED" and the given marks.
     *
     * @throws DuplicateLabException If the given {@code LabMark} is equal to the existing {@code LabMark}.
     */
    public Lab editLabMark(LabMark mark) throws DuplicateLabException {
        if (mark.equals(labMark)) {
            throw new DuplicateLabException();
        }
        return new Lab(String.valueOf(labNumber), mark);
    }

    /**
     * Returns a new immutable lab with the same attributes as this.
     */
    public Lab of(String labStatusString) {
        requireNonNull(labStatusString);
        return of(LabStatus.toLabStatus(labStatusString));
    }

    /**
     * Returns a new immutable {@code Lab} with the specified {@code LabStatus}
     * but no {@code LabMark} initialized.
     *
     * @throws InvalidLabStatusException If {@code LabStatus} is LabStatus.GRADED.
     */
    public Lab of(LabStatus labStatus) throws InvalidLabStatusException {
        requireNonNull(labStatus);
        if (labStatus == LabStatus.GRADED) {
            throw new InvalidLabStatusException();
        }
        return new Lab(String.valueOf(labNumber), labStatus);
    }

    /**
     * Returns a new immutable {@code Lab} with the specified {@code LabMark}.
     */
    public Lab of(LabMark labMark) {
        requireNonNull(labMark);
        if (labMark.isEmpty()) {
            throw new InvalidLabStatusException();
        }
        return new Lab(String.valueOf(labNumber), labMark);
    }

    /**
     * Returns a new immutable {@code Lab} with the specified {@code LabStatus} and {@code LabMark}.
     * {@code LabMark.MARKS_UNKNOWN} is assigned to labs with {@code LabStatus == UNSUBMITTED / SUBMITTED}.
     */
    public Lab of(LabStatus labStatus, LabMark labMark) {
        requireAllNonNull(labStatus, labMark);

        if (labStatus == LabStatus.GRADED) {
            return of(labMark);
        } else {
            return of(labStatus);
        }

    }

    /**
     * Returns a new immutable {@code Lab} with the specified {@code LabMark}.
     * Mainly used in {@link SampleDataUtil} and JsonAdaptedStudent.
     */
    public Lab of(String labStatus, String labMark) throws InvalidLabStatusException {
        requireAllNonNull(labStatus, labMark);

        if (labMark.equals(LabMark.MARKS_UNKNOWN)) {
            return (new Lab(String.valueOf(labNumber))).of(labStatus);
        }

        return (new Lab(String.valueOf(labNumber))).of(new LabMark(labMark));
    }

    /**
     * Returns true if both Labs have the same lab number.
     * This defines a weaker notion of equality between two Labs.
     */
    public boolean isSameLab(Lab otherLab) {
        requireNonNull(otherLab);

        if (otherLab == this) {
            return true;
        }

        return otherLab.labNumber == this.labNumber;
    }

    /**
     * Returns true if both labs have the same {@code labStatus}
     */
    public boolean hasSameLabStatus(Lab otherLab) {
        requireNonNull(otherLab);

        if (otherLab == this) {
            return true;
        }

        return otherLab.labStatus.equals(this.labStatus);

    }

    /**
     * Returns the details of a Lab in {@code String} format
     */
    public String getDetails() {
        if (labMark.isEmpty()) {
            return labStatus.describe();
        } else {
            return labMark.describe();
        }
    }

    @Override
    public String toString() {
        return "Lab " + labNumber;
    }

    /**
     * Returns true if both Labs have the same lab number, LabStatus and LabMark.
     * This defines a stronger notion of equality between two Labs.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNumber == (((Lab) other).labNumber) // labNumber check
                && labStatus == (((Lab) other).labStatus)) // labStatus check
                && labMark.equals(((Lab) other).labMark); // labMark check
    }

}
