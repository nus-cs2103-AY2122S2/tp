package seedu.address.testutil;

import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;

/**
 * A utility class to help with building Prescription objects.
 */
public class PrescriptionBuilder {

    public static final String DEFAULT_NRIC = "S7654321L";
    public static final String DEFAULT_DRUG_NAME = "Ativan";
    public static final String DEFAULT_DATE = "2022-04-08";
    public static final String DEFAULT_INSTRUCTION = "2 tablets after meal everyday";

    private Nric nric;
    private DrugName drugName;
    private PrescriptionDate date;
    private Instruction instruction;

    /**
     * Creates a {@code PrescriptionBuilder} with the default details.
     */
    public PrescriptionBuilder() {
        nric = new Nric(DEFAULT_NRIC);
        drugName = new DrugName(DEFAULT_DRUG_NAME);
        date = new PrescriptionDate(DEFAULT_DATE);
        instruction = new Instruction(DEFAULT_INSTRUCTION);
    }

    /**
     * Initializes the Prescription with the data of {@code prescriptionToCopy}.
     */
    public PrescriptionBuilder(Prescription prescriptionToCopy) {
        nric = prescriptionToCopy.getPrescriptionTarget();
        drugName = prescriptionToCopy.getDrugName();
        date = prescriptionToCopy.getPrescriptionDate();
        instruction = prescriptionToCopy.getInstruction();
    }

    /**
     * Sets the {@code Nric} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code DrugName} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withDrugName(String name) {
        this.drugName = new DrugName(name);
        return this;
    }

    /**
     * Sets the {@code PrescriptionDate} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withDate(String date) {
        this.date = new PrescriptionDate(date);
        return this;
    }

    /**
     * Sets the {@code Instruction} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withInstruction(String instruction) {
        this.instruction = new Instruction(instruction);
        return this;
    }

    public Prescription build() {
        return new Prescription(nric, drugName, date, instruction);
    }
}
