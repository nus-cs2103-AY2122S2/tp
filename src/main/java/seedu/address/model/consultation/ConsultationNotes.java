package seedu.address.model.consultation;

/**
 * Represents a Consultation's TestsTakenAndResults in the address book.
 */
public class ConsultationNotes {

    public static final String MESSAGE_CONSTRAINTS =
            "Notes can be blank";

    private String consultationNotes;

    /**
     * Constructs a {@code TestsTakenAndResults}.
     */
    public ConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public String value() {
        return consultationNotes;
    }


    @Override
    public String toString() {
        return consultationNotes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationNotes // instanceof handles nulls
                && consultationNotes.equals(((ConsultationNotes) other).consultationNotes)); // state check
    }

    @Override
    public int hashCode() {
        return consultationNotes.hashCode();
    }

}
