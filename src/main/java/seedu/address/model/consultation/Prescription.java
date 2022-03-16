package seedu.address.model.consultation;

/**
 * Represents a Person's Notes in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Prescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Prescription can be blank";

    public String prescription;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Prescription(String prescription) {
        this.prescription = prescription;
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
