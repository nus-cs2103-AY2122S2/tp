package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;

import seedu.address.model.person.Nric;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Notes;
import seedu.address.model.consultation.Prescription;
import seedu.address.model.consultation.TestsTakenAndResults;


/**
 * Jackson-friendly version of {@link Contact}.
 */
class JsonAdaptedConsultation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";

    private final Nric ownerNric;
    private final Date date;
    private final Time time;
    private final Notes notes;
    private final Prescription prescription;
    private final TestsTakenAndResults testsTakenAndResults;

    /**
     * Constructs a {@code JsonAdaptedContact} with the given contact details.
     */
    @JsonCreator
    public JsonAdaptedConsultation(@JsonProperty("ownerNric") Nric ownerNric, @JsonProperty("date") Date date,
                              @JsonProperty("time") Time time, @JsonProperty("notes") Notes notes,
                              @JsonProperty("prescription") Prescription prescription, @JsonProperty("testsTakenAndResults")
                                      TestsTakenAndResults testsTakenAndResults) {

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
        ownerNric = source.getNric();
        date = source.getDate();
        time = source.getTime();
        notes = source.getNotes();
        prescription = source.getPrescription();
        testsTakenAndResults = source.getTestAndResults();
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
        if (!Nric.isValidNric(ownerNric.toString())) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelOwnerNric = ownerNric;

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date.toString())) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = date;

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time.toString())) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = time;

        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Notes.class.getSimpleName()));
        }
        if (!Notes.isValid(notes.toString())) {
            throw new IllegalValueException(Notes.MESSAGE_CONSTRAINTS);
        }
        final Notes modelNotes = notes;

        if (prescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Prescription.class.getSimpleName()));
        }

        final Prescription modelPrescription = prescription;

        final TestsTakenAndResults modelTestsTakenAndResults = testsTakenAndResults;


        return new Consultation(modelOwnerNric, modelDate, modelTime, modelNotes, modelPrescription, modelTestsTakenAndResults);
    }
}