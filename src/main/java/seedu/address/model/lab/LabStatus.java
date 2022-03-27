package seedu.address.model.lab;

import seedu.address.model.student.exceptions.InvalidLabStatusException;

import static java.util.Objects.requireNonNull;

/**
 * Represents the possible LabStatuses a Lab can have.
 */
public enum LabStatus {
    UNSUBMITTED, SUBMITTED, GRADED;

    public static final String MESSAGE_CONSTRAINTS =
            "LabStatus should be u, s, or g, which respectively stands for "
                    + "Unsubmitted, Submitted, and Graded";

    public static final String VALIDATION_REGEX = "[usg]";

    /**
     * Returns true if a given string is a valid LabStatus
     */
    public static boolean isValidLabStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parses a String given by {@code labStatusString} into a LabStatus.
     */
    public static LabStatus toLabStatus(String labStatusString) {
        requireNonNull(labStatusString);

        switch (labStatusString) {
        case "UNSUBMITTED":
            return LabStatus.UNSUBMITTED;
        case "SUBMITTED":
            return LabStatus.SUBMITTED;
        case "GRADED":
            return LabStatus.GRADED;
        default:
            throw new IllegalArgumentException("Did not expect " + labStatusString);
        }
    }

    /**
     * Describes the labStatus for the user
     *
     * @param labStatus
     * @return Description of the labStatus in {@code String} format
     */
    public static String describe(LabStatus labStatus) {
        requireNonNull(labStatus);

        switch (labStatus) {
        case UNSUBMITTED:
            return "Not Submitted";
        case SUBMITTED:
            return "Submitted but yet to grade";
        case GRADED:
            return "Graded";
        default:
            throw new InvalidLabStatusException();
        }
    }

}
