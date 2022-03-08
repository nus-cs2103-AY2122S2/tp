package seedu.address.model.person.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.model.person.exceptions.LabAlreadyGradedException;
import seedu.address.model.person.exceptions.LabAlreadySubmittedException;
import seedu.address.model.person.exceptions.LabNotSubmittedException;


/**
 * Represents a Lab entry.
 * Guarantees: immutable; is valid as declared in {@link #isValidLab(String)}
 */
public class Lab {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab number should be a valid Integer";

    /*
     * Lab number has to be an Integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final int labNumber;

    public final LabStatus labStatus;

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
     * Constructs an {@code Lab}.
     *
     * @param labNumber A valid lab number.
     * @param labStatus The status of the Lab to be created.
     */
    public Lab(String labNumber, LabStatus labStatus) {
        requireNonNull(labNumber);
        checkArgument(isValidLab(labNumber), MESSAGE_CONSTRAINTS);
        this.labNumber = Integer.parseInt(labNumber);
        this.labStatus = labStatus;
    }

    /**
     * Returns true if a given string is a valid Lab.
     */
    public static boolean isValidLab(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a new immutable lab with labStatus equals to {@code LabStatus.SUBMITTED}
     */
    public Lab submitLab() {
        if (labStatus == LabStatus.SUBMITTED || labStatus == LabStatus.GRADED) {
            throw new LabAlreadySubmittedException(labNumber);
        }

        return new Lab(String.valueOf(labNumber), LabStatus.SUBMITTED);
    }

    /**
     * Returns a new immutable lab with labStatus equals to {@code LabStatus.GRADED}
     */
    public Lab gradeLab() {
        if (labStatus == LabStatus.UNSUBMITTED) {
            throw new LabNotSubmittedException(labNumber);
        }

        if (labStatus == LabStatus.GRADED) {
            throw new LabAlreadyGradedException(labNumber);
        }

        return new Lab(String.valueOf(labNumber), LabStatus.GRADED);
    }

    /**
     * Returns a new immutable lab with the same attributes as this.
     */
    public Lab createCopy() {
        return new Lab(String.valueOf(labNumber), labStatus);
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameLab(Lab otherLab) {
        if (otherLab == this) {
            return true;
        }

        return otherLab != null
                && otherLab.labNumber == this.labNumber;
    }

    @Override
    public String toString() {
        return "Lab " + labNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNumber == (((Lab) other).labNumber)) // state check
                && labStatus == ((Lab) other).labStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labNumber, labStatus);
    }

}
