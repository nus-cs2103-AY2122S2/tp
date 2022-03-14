package seedu.address.model.person.lab;

import seedu.address.logic.parser.exceptions.ParseException;

public enum LabStatus {
    UNSUBMITTED, SUBMITTED, GRADED;

    private static final String VALIDATION_REGEX = "[usg]";
    public static final String MESSAGE_CONSTRAINTS =
            "LabStatus should be u, s, or g, which respectively stands for "
            + "Unsubmitted, Submitted, and Graded";

    /**
     * Returns true if a given string is a valid LabStatus
     * @param test
     */
    public static boolean isValidLabStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Maps the string given into LabStatus
     * @param toMap String to be mapped
     * @return {@code LabStatus} corresponding to the String given
     * @throws ParseException when invalid LabStatus is given
     */
    public static LabStatus map(String toMap) throws ParseException {

        switch (toMap) {
        case "u":
            return LabStatus.UNSUBMITTED;
        case "s":
            return LabStatus.SUBMITTED;
        case "g":
            return LabStatus.GRADED;
        default:
            throw new IllegalArgumentException("Did not expect " + toMap);
        }

    }

}
