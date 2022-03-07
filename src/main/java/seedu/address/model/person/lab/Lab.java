package seedu.address.model.person.lab;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.exceptions.LabAlreadyGradedException;
import seedu.address.model.person.exceptions.LabAlreadySubmittedException;
import seedu.address.model.person.exceptions.LabNotSubmittedException;


/**
 * Represents a Lab entry in the address book.
 * Is valid as declared in {@link #isValidLab(String)}
 */
public class Lab {

    public static final String MESSAGE_CONSTRAINTS =
            "Lab number should be a valid Integer";

    /*
     * Lab number has to be an Integer.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final int labNumber;

    private LabStatus labStatus;

    /**
     * Constructs an {@code Lab}.
     *
     * @param labNumber A valid lab number.
     */
    public Lab(String labNumber) {
        requireNonNull(labNumber);
        checkArgument(isValidLab(labNumber), MESSAGE_CONSTRAINTS);
        this.labNumber = Integer.parseInt(labNumber);
        setLabStatus(LabStatus.UNSUBMITTED); // labStatus is always initialized to {@code LabStatus.UNSUBMITTED}
    }

    /**
     * Returns true if a given string is a valid Lab.
     */
    public static boolean isValidLab(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private void setLabStatus(LabStatus labStatus) {
        this.labStatus = labStatus;
    }

    /**
     * Sets the lab status of the lab to {@code LabStatus.SUBMITTED}
     */
    public void submitLab() {
        if (this.labStatus == LabStatus.SUBMITTED) {
            throw new LabAlreadySubmittedException(labNumber);
        }

        setLabStatus(LabStatus.SUBMITTED);
    }

    /**
     * Sets the lab status of the lab to {@code LabStatus.GRADED}
     */
    public void gradeLab() {
        if (this.labStatus == LabStatus.UNSUBMITTED) {
            throw new LabNotSubmittedException(labNumber);
        }

        if (this.labStatus == LabStatus.GRADED) {
            throw new LabAlreadyGradedException(labNumber);
        }

        setLabStatus(LabStatus.GRADED);
    }

    @Override
    public String toString() {
        return "Lab " + labNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNumber == (((Lab) other).labNumber)); // state check
    }

    @Override
    public int hashCode() {
        return ("Lab " + labNumber).hashCode();
    }
}
