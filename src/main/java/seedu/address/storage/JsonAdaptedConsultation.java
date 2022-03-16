package seedu.address.storage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Notes;
import seedu.address.model.consultation.Prescription;
import seedu.address.model.consultation.TestsTakenAndResults;
import seedu.address.model.patient.Nric;


/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedConsultation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final String ownerNric;
    private final String date;
    private final String time;
    private final String notes;
    private final String prescription;
    private final String testsTakenAndResults;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("ownerNric") String ownerNric, @JsonProperty("date") String date,
                              @JsonProperty("time") String time, @JsonProperty("notes") String notes,
                              @JsonProperty("prescription") String prescription, @JsonProperty("testsTakenAndResults")
                                               String testsTakenAndResults) {

        this.ownerNric = ownerNric;
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.prescription = prescription;
        this.testsTakenAndResults = testsTakenAndResults;
    }

    /**
     * Converts a given {@code Contact} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        ownerNric = source.getNric().value;
        date = source.getDate().date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = source.getTime().time.format(DateTimeFormatter.ofPattern("HH-mm"));
        notes = source.getNotes().notes;
        prescription = source.getPrescription().prescription;
        testsTakenAndResults = source.getTestAndResults().testsTakenAndResults;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Contact} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
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

        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Notes.class.getSimpleName()));
        }
        if (!Notes.isValid(notes)) {
            throw new IllegalValueException(Notes.MESSAGE_CONSTRAINTS);
        }
        final String modelNotes = notes;

        final String modelPrescription = prescription;

        final String modelTestsTakenAndResults = testsTakenAndResults;


        return new Consultation(new Nric(modelOwnerNric), new Date(modelDate), new Time(modelTime),
                new Notes(modelNotes), new Prescription(modelPrescription),
                new TestsTakenAndResults(modelTestsTakenAndResults));
    }
}
