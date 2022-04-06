package seedu.address.testutil;

import seedu.address.logic.commands.prescription.EditPrescriptionCommand.EditPrescriptionDescriptor;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;

public class EditPrescriptionDescriptorBuilder {

    private EditPrescriptionDescriptor descriptor;

    public EditPrescriptionDescriptorBuilder() {
        descriptor = new EditPrescriptionDescriptor();
    }

    public EditPrescriptionDescriptorBuilder(EditPrescriptionDescriptor descriptor) {
        this.descriptor = new EditPrescriptionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPrescriptionDescriptorBuilder(Prescription prescription) {
        descriptor = new EditPrescriptionDescriptor();
        descriptor.setDrugName(prescription.getDrugName());
        descriptor.setPrescriptionDate(prescription.getPrescriptionDate());
        descriptor.setInstruction(prescription.getInstruction());
    }

    /**
     * Sets the {@code DrugName} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withDrugName(String name) {
        descriptor.setDrugName(new DrugName(name));
        return this;
    }

    /**
     * Sets the {@code PrescriptionDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withDate(String date) {
        descriptor.setPrescriptionDate(new PrescriptionDate(date));
        return this;
    }

    /**
     * Sets the {@code Instruction} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withInstruction(String instruction) {
        descriptor.setInstruction(new Instruction(instruction));
        return this;
    }

    public EditPrescriptionDescriptor build() {
        return descriptor;
    }
}
