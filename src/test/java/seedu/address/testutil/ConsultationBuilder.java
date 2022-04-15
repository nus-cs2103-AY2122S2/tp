package seedu.address.testutil;

import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDiagnosis;
import seedu.address.model.consultation.ConsultationFee;
import seedu.address.model.consultation.ConsultationNotes;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.patient.Nric;

/**
 * A utility class to help with building Consultation objects.
 */
public class ConsultationBuilder {

    public static final String DEFAULT_NRIC = "S7654321L";

    private Nric nric;
    private Date date;
    private Time time;
    private ConsultationDiagnosis diagnosis;
    private ConsultationFee fee;
    private ConsultationNotes notes;


    /**
     * Creates a {@code ConsultationBuilder} with the default details.
     */
    public ConsultationBuilder() {
        nric = new Nric(DEFAULT_NRIC);
    }

    /**
     * Sets the {@code Nric} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code ConsultationDiagnosis} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withDiagnosis(String diagnosis) {
        this.diagnosis = new ConsultationDiagnosis(diagnosis);
        return this;
    }

    /**
     * Sets the {@code ConsultationFee} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withFee(String fee) {
        this.fee = new ConsultationFee(fee);
        return this;
    }

    /**
     * Sets the {@code ConsultationNotes} of the {@code Consultation} that we are building.
     */
    public ConsultationBuilder withNotes(String notes) {
        this.notes = new ConsultationNotes(notes);
        return this;
    }

    /**
     * Builds {@code Consultation} object
     */
    public Consultation build() {
        return new Consultation(
                nric,
                date,
                time,
                diagnosis,
                fee,
                notes
        );
    }
}
