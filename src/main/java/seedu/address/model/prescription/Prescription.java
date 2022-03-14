package seedu.address.model.prescription;

import seedu.address.model.person.Nric;

import java.util.Objects;

public class Prescription {

    private final Nric patient;
    private final DrugName name;
    private final PrescriptionDate date;
    private final Instruction instruction;

    public Prescription(Nric patient, DrugName name, PrescriptionDate date, Instruction instruction) {
        this.patient = patient;
        this.name = name;
        this.date = date;
        this.instruction = instruction;
    }

    public Nric getPrescriptionTarget() {
        return patient;
    }

    public DrugName getDrugName() {
        return name;
    }

    public PrescriptionDate getPrescriptionDate() {
        return date;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return otherPrescription.getPrescriptionTarget().equals(getPrescriptionTarget())
                && otherPrescription.getDrugName().equals(getDrugName())
                && otherPrescription.getPrescriptionDate().equals(getPrescriptionDate())
                && otherPrescription.getInstruction().equals(getInstruction());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patient, name, date, instruction);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPrescriptionTarget())
                .append("; DrugName: ")
                .append(getDrugName())
                .append("; Date: ")
                .append(getPrescriptionDate())
                .append("; Instruction: ")
                .append(getInstruction());

        return builder.toString();
    }
}
