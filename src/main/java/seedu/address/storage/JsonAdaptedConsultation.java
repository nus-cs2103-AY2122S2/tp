package seedu.address.storage;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDiagnosis;
import seedu.address.model.consultation.ConsultationFee;
import seedu.address.model.consultation.ConsultationNotes;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.patient.Nric;


/**
 * Jackson-friendly version of {@link Consultation}.
 */
class JsonAdaptedConsultation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final String ownerNric;
    private final String date;
    private final String time;
    private final String diagnosis;
    private final String fee;
    private final String notes;

    /**
     * Constructs a {@code JsonAdaptedConsultation} with the given consultation details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("ownerNric") String ownerNric, @JsonProperty("date") String date,
                              @JsonProperty("time") String time, @JsonProperty("diagnosis") String diagnosis,
                              @JsonProperty("fee") String fee, @JsonProperty("notes")
                                               String notes) {

        this.ownerNric = ownerNric;
        this.date = date;
        this.time = time;
        this.diagnosis = diagnosis;
        this.fee = fee;
        this.notes = notes;
    }

    /**
     * Converts a given {@code Consultation} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        ownerNric = source.getNric().value;
        date = source.getDate().value().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        time = source.getTime().value().format(DateTimeFormatter.ofPattern("HH-mm"));
        diagnosis = source.getDiagnosis().value();
        fee = source.getFee().value();
        notes = source.getNotes().value();
    }

    /**
     * Converts this Jackson-friendly adapted consultation object into the model's {@code Consultation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted consultation.
     */
    public Consultation toModelType() throws IllegalValueException {

        if (ownerNric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(ownerNric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final String modelOwnerNric = ownerNric;


        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final String modelDate = date;


        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final String modelTime = time;


        if (diagnosis == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ConsultationDiagnosis.class.getSimpleName()));
        }
        if (!ConsultationDiagnosis.isValidDiagnosis(diagnosis)) {
            throw new IllegalValueException(ConsultationDiagnosis.MESSAGE_CONSTRAINTS);
        }
        final String modelDiagnosis = diagnosis;


        if (fee == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ConsultationFee.class.getSimpleName()));
        }
        if (!ConsultationFee.isValidFee(fee)) {
            throw new IllegalValueException(ConsultationFee.MESSAGE_CONSTRAINTS);
        }
        final String modelFee = fee;

        final String modelNotes = notes;

        return new Consultation(new Nric(modelOwnerNric), new Date(modelDate), new Time(modelTime),
                new ConsultationDiagnosis(modelDiagnosis), new ConsultationFee(modelFee),
                new ConsultationNotes(modelNotes));
    }
}
