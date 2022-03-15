package seedu.address.model.person.lab;

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

}
