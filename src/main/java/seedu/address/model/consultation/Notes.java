package seedu.address.model.consultation;

/**
 * Represents a Consultation's Notes in the address book.
 */
public class Notes {

    private String notes;

    /**
     * Constructs a {@code Notes}.
     *
     * @param notes A valid notes.
     */
    public Notes(String notes) {
        this.notes = notes;
    }

    public String value() {
        return notes;
    }
    
    @Override
    public String toString() {
        return notes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && notes.equals(((Notes) other).notes)); // state check
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }

}
