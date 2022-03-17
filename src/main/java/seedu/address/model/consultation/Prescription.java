package seedu.address.model.consultation;

/**
 * Represents a Consultation's Prescription in the address book.
 */
public class Prescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Prescription can be blank";

    private String prescription;

    /**
     * Constructs a {@code Prescription}.
     *
     * @param prescription A valid prescription.
     */
    public Prescription(String prescription) {
        this.prescription = prescription;
    }

    public String value() {
        return prescription;
    }


    @Override
    public String toString() {
        return prescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Prescription // instanceof handles nulls
                && prescription.equals(((Prescription) other).prescription)); // state check
    }

    @Override
    public int hashCode() {
        return prescription.hashCode();
    }

}
