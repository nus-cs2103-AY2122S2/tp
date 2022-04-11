package seedu.address.testutil;

import seedu.address.logic.commands.EditProcCommand.EditProcedureDescriptor;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.model.procedure.Procedure;

/**
 * A utility class to help with building EditProcedureDescriptor objects.
 */
public class EditProcedureDescriptorBuilder {

    private EditProcedureDescriptor descriptor;

    public EditProcedureDescriptorBuilder() {
        descriptor = new EditProcedureDescriptor();
    }

    public EditProcedureDescriptorBuilder(EditProcedureDescriptor descriptor) {
        this.descriptor = new EditProcedureDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProcedureDescriptorBuilder} with fields containing {@code Procedure}'s details
     */
    public EditProcedureDescriptorBuilder(Procedure procedure) {
        descriptor = new EditProcedureDescriptor();
        descriptor.setInfo(procedure.getInfo());
        descriptor.setDate(procedure.getDate());
        descriptor.setCost(procedure.getCost());
    }

    /**
     * Sets the {@code Information} of the {@code EditProcedureDescriptor} that we are building.
     */
    public EditProcedureDescriptorBuilder withInfo(String info) {
        descriptor.setInfo(new Information(info));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditProcedureDescriptor} that we are building.
     */
    public EditProcedureDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code EditProcedureDescriptor} that we are building.
     */
    public EditProcedureDescriptorBuilder withCost(String cost) {
        descriptor.setCost(new Cost(cost));
        return this;
    }

    public EditProcedureDescriptor build() {
        return descriptor;
    }
}
