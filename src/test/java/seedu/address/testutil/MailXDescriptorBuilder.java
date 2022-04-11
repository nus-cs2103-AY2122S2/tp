package seedu.address.testutil;

import seedu.address.logic.commands.MailXCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentNumber;


public class MailXDescriptorBuilder {
    private MailXCommand.MailXDescriptor descriptor;

    public MailXDescriptorBuilder() {
        descriptor = new MailXCommand.MailXDescriptor();
    }

    public MailXDescriptorBuilder(MailXCommand.MailXDescriptor descriptor) {
        this.descriptor = new MailXCommand.MailXDescriptor(descriptor);
    }

    /**
     * Returns a {@code MailXDescriptor} with fields containing {@code person}'s details
     */
    public MailXDescriptorBuilder(Person person) {
        descriptor = new MailXCommand.MailXDescriptor();
        descriptor.setName(person.getName());
        descriptor.setStudentNumber(person.getStudentNumber());
        descriptor.setEmail(person.getEmail());
        descriptor.setMod(person.getMod());
    }

    /**
     * Sets the {@code Name} of the {@code MailXDescriptor} that we are building.
     */
    public MailXDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code MailXDescriptor} that we are building.
     */
    public MailXDescriptorBuilder withStudentNumber(String studentNumber) {
        descriptor.setStudentNumber(new StudentNumber(studentNumber));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code MailXDescriptor} that we are building.
     */
    public MailXDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Mod} of the {@code MailXDescriptor} that we are building.
     */
    public MailXDescriptorBuilder withMod(String address) {
        descriptor.setMod(new Mod(address));
        return this;
    }

    public MailXCommand.MailXDescriptor build() {
        return descriptor;
    }

    /**
     * Sets the {@code Group} of the {@code MailXDescriptor} that we are building.
     */
    public MailXDescriptorBuilder withGroup(String address) {
        descriptor.setGroup(new Group(address));
        return this;
    }
}
