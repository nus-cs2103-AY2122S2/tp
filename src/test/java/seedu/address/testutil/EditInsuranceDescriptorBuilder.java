package seedu.address.testutil;

import seedu.address.logic.commands.EditInsuranceCommand.EditInsuranceDescriptor;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditInsuranceDescriptorBuilder {

    private EditInsuranceDescriptor descriptor;

    public EditInsuranceDescriptorBuilder() {
        descriptor = new EditInsuranceDescriptor();
    }

    public EditInsuranceDescriptorBuilder(EditInsuranceDescriptor descriptor) {
        this.descriptor = new EditInsuranceDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInsuranceDescriptor} with fields containing {@code insurance}'s details
     */
    public EditInsuranceDescriptorBuilder(Insurance insurance) {
        descriptor = new EditInsuranceDescriptor();
        descriptor.setTitle(insurance.getTitle());
        descriptor.setPrice(insurance.getPrice());
    }

    /**
     * Sets the {@code Title} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditInsuranceDescriptor} that we are building.
     */
    public EditInsuranceDescriptorBuilder withPrice(String price) {
        descriptor.setPrice((new Price(price)));
        return this;
    }

    public EditInsuranceDescriptor build() {
        return descriptor;
    }
}
