package seedu.address.testutil;

import seedu.address.logic.commands.EditDriverCommand;
import seedu.address.logic.commands.EditDriverCommand.EditDriverDescriptor;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.DriverStatus;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;

/**
 * A utility class to help with building EditDriverDescriptor objects.
 */
public class EditDriverDescriptorBuilder {

    private final EditDriverCommand.EditDriverDescriptor descriptor;

    public EditDriverDescriptorBuilder() {
        descriptor = new EditDriverDescriptor();
    }

    public EditDriverDescriptorBuilder(EditDriverCommand.EditDriverDescriptor descriptor) {
        this.descriptor = new EditDriverDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditDriverDescriptor} with fields containing {@code driver}'s details
     */
    public EditDriverDescriptorBuilder(Driver driver) {
        descriptor = new EditDriverCommand.EditDriverDescriptor();
        descriptor.setName(driver.getName());
        descriptor.setPhone(driver.getPhone());
        descriptor.setStatus(DriverStatus.FREE);
    }

    /**
     * Sets the {@code NameDriver} of the {@code EditDriverDescriptor} that we are building.
     */
    public EditDriverDescriptorBuilder withName(String name) {
        descriptor.setName(new NameDriver(name));
        return this;
    }

    /**
     * Sets the {@code PhoneDriver} of the {@code EditDriverDescriptor} that we are building.
     */
    public EditDriverDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new PhoneDriver(phone));
        return this;
    }

    /**
     * Sets the {@code DriverStatus} of the {@code EditDriverDescriptor} that we are building.
     */
    public EditDriverDescriptorBuilder withStatus(DriverStatus status) {
        descriptor.setStatus(status);
        return this;
    }

    public EditDriverCommand.EditDriverDescriptor build() {
        return descriptor;
    }
}
