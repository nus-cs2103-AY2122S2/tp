package seedu.address.model.consultation;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.patient.Nric;

/**
 * Represents a Consultation in the address book.
 */
public class Consultation {

    // Identity fields
    private Nric nric;

    // Data fields
    private Date date;
    private Time time;
    private ConsultationDiagnosis diagnosis;
    private ConsultationFee fee;
    private ConsultationNotes notes;

    /**
     * Every field except notes, prescription, and testsTakenAndResults must be present and not null.
     */
    public Consultation(Nric nric, Date date, Time time, ConsultationDiagnosis diagnosis,
                        ConsultationFee fee, ConsultationNotes notes) {
        requireAllNonNull(nric, date, time);
        this.nric = nric;
        this.date = date;
        this.time = time;
        this.diagnosis = diagnosis;
        this.fee = fee;
        this.notes = notes;
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

    public ConsultationDiagnosis getDiagnosis() {
        return diagnosis;
    }

    public ConsultationFee getFee() {
        return fee;
    }

    public ConsultationNotes getNotes() {
        return notes;
    }

    /**
     * Returns true if both consultations have the same nric, date, time fields.
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
                && otherConsultation.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, date, time, diagnosis, fee, notes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Nric: ")
                .append(getNric())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime())
                .append(" Diagnosis: ")
                .append(getDiagnosis())
                .append(" Fee: ")
                .append(getFee())
                .append(" Notes: ")
                .append(getNotes());

        return builder.toString();
    }

}

