package seedu.address.model.lab;

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

}
