package seedu.address.model.consultation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import seedu.address.model.person.Nric;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Consultation {

    // Identity fields
    private Nric nric;

    // Data fields
    private Date date;
    private Time time;
    private Notes notes;
    private Prescription prescription;
    private TestsTakenAndResults testsTakenAndResults;

    /**
     * Every field must be present and not null.
     */
    public Consultation(Nric nric, Date date, Time time, Notes notes,
                            Prescription prescription, TestsTakenAndResults testsTakenAndResults) {
        requireAllNonNull(nric, date, time, notes);
        this.nric = nric;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.prescription = prescription;
        this.testsTakenAndResults = testsTakenAndResults;
    }

    public Nric getNric() {
        return nric;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public Notes getNotes() {
        return notes;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public TestsTakenAndResults getTestAndResults() {
        return testsTakenAndResults;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Consultation)) {
            return false;
        }

        Consultation otherConsultation = (Consultation) other;
        return otherConsultation.getNric().equals(getNric())
                && otherConsultation.getDate().equals(getDate())
                && otherConsultation.getTime().equals(getTime())
                && otherConsultation.getNotes().equals(getNotes())
                && otherConsultation.getPrescription().equals(getPrescription())
                && otherConsultation.getTestAndResults().equals(getTestAndResults());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, date, time, notes, prescription, testsTakenAndResults);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
                .append("; Date: ")
                .append(getDate())
                .append("; Time: ")
                .append(getTime())
                .append("; Notes: ")
                .append(getNotes())
                .append("; Prescription: ")
                .append(getPrescription())
                .append("; Tests and Results: ")
                .append(getTestAndResults());

        return builder.toString();
    }

}

