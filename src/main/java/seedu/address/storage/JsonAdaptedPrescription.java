package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Phone;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;

public class JsonAdaptedPrescription {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String nric;
    private final String drugname;
    private final String date;
    private final String instruction;

    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("nric") String nric, @JsonProperty("drugname") String drugname,
                              @JsonProperty("date") String date, @JsonProperty("instruction") String instruction) {

        this.nric = nric;
        this.drugname = drugname;
        this.date = date;
        this.instruction = instruction;
    }

    public JsonAdaptedPrescription(Prescription source) {
        nric = source.getPrescriptionTarget().value;
        drugname = source.getDrugName().drugName;
        date = source.getPrescriptionDate().toString();
        instruction = source.getInstruction().value;
    }

    public Prescription toModelType() throws IllegalValueException {

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (drugname == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DrugName.class.getSimpleName()));
        }
        if (!DrugName.isValidName(drugname)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final DrugName modelDrugName = new DrugName(drugname);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PrescriptionDate.class.getSimpleName()));
        }
        if (!PrescriptionDate.isValidDate(date)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final PrescriptionDate modelDate = new PrescriptionDate(date);

        if (instruction == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Instruction.class.getSimpleName()));
        }
        if (!Instruction.isValidInstruction(instruction)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Instruction modelInstruction = new Instruction(instruction);

        return new Prescription(modelNric, modelDrugName, modelDate, modelInstruction);
    }
}
