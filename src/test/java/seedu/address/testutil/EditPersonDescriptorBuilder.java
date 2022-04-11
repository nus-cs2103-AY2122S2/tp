package seedu.address.testutil;

import java.util.Arrays;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setEducations(person.getEducations());
        descriptor.setInternships(person.getInternships());
        descriptor.setModules(person.getModules());
        descriptor.setCcas(person.getCcas());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Education} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEducation(String education) {
        descriptor.setEducations(Arrays.asList(new Education(education)));
        return this;
    }

    /**
     * Sets the {@code Internship} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInternship(String internship) {
        descriptor.setInternships(Arrays.asList(new Education(internship)));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModule(String module) {
        descriptor.setModules(Arrays.asList(new Module(module)));
        return this;
    }

    /**
     * Sets the {@code Cca} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCca(String cca) {
        descriptor.setCcas(Arrays.asList(new Cca(cca)));
        return this;
    }


    public EditPersonDescriptor build() {
        return descriptor;
    }
}
