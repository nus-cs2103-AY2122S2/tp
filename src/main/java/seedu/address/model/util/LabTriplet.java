package seedu.address.model.util;

public class LabTriplet {
    public static final String EMPTY_MARK = "Unknown";

    private String labNumber;
    private String labStatus;
    private String labMark;

    /**
     * Creates a LabTriplet containing String representations of each Lab attribute.
     */
    public LabTriplet(String number, String status, String mark) {
        labNumber = number;
        labStatus = status;
        labMark = mark;
    }

    /**
     * Creates a LabTriplet containing String representations of each Lab attribute.
     * For when no marks are initialized.
     */
    public LabTriplet(String number, String status) {
        labNumber = number;
        labStatus = status;
        labMark = EMPTY_MARK;
    }

    public String getLabNumber() {
        return labNumber;
    }

    public String getLabStatus() {
        return labStatus;
    }

    public String getLabMark() {
        return labMark;
    }
}
