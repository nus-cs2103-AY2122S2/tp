package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    // General messages
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    // Patient related messages
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";

    // Contact related messages
    public static final String MESSAGE_CONTACTS_LISTED_OVERVIEW = "%1$d contacts listed! Belongs to: %2$s";
    public static final String MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX =
            "The contact index provided is invalid";

    // Consultation related messages
    public static final String MESSAGE_CONSULTATION_LISTED_OVERVIEW = "%1$d consultations listed! Belongs to: %2$s";
    public static final String MESSAGE_INVALID_CONSULTATION_INDEX =
            "The consultation index provided is invalid";

    // Medical related messages
    public static final String MESSAGE_MEDICALS_LISTED_NO_NRIC = "%1$d medical information listed!";
    public static final String MESSAGE_MEDICALS_LISTED_OVERVIEW = "%1$d medical information listed! Belongs to: %2$s";
    public static final String MESSAGE_INVALID_MEDICAL_INFORMATION_INDEX =
            "The medical information index provided is invalid";

    // Prescription related messages
    public static final String MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW = "%1$d prescription listed! Belongs to: %2$s";
    public static final String MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX =
            "The prescription index provided is invalid";

    // Test related messages
    public static final String MESSAGE_TEST_RESULTS_LISTED_OVERVIEW = "%1$d test results listed! Belongs to: %2$s";
    public static final String MESSAGE_INVALID_TEST_RESULT_INDEX = "The test result index provided is invalid";

    // Summary related messages
    public static final String MESSAGE_SUMMARY_SHOWN = "Summary for patient with NRIC %1$s shown!";
}
