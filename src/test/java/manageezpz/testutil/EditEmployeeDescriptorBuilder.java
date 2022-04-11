package manageezpz.testutil;

import manageezpz.logic.commands.EditEmployeeCommand;
import manageezpz.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import manageezpz.model.person.Email;
import manageezpz.model.person.Name;
import manageezpz.model.person.Person;
import manageezpz.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditEmployeeDescriptorBuilder {

    private EditEmployeeDescriptor descriptor;

    public EditEmployeeDescriptorBuilder() {
        descriptor = new EditEmployeeDescriptor();
    }

    public EditEmployeeDescriptorBuilder(EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditEmployeeCommand.EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditEmployeeDescriptorBuilder(Person person) {
        descriptor = new EditEmployeeCommand.EditEmployeeDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }


    public EditEmployeeDescriptor build() {
        return descriptor;
    }
}
