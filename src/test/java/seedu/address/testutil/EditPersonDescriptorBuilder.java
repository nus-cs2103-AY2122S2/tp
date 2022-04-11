package seedu.address.testutil;

import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonAmount;

public class EditPersonDescriptorBuilder {
    private EditPersonCommand.EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonCommand.EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonCommand.EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonCommand.EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonCommand.EditPersonDescriptor();
        descriptor.setPersonAmount(person.getPersonAmount());
    }

    /**
     * Sets the {@code PersonAmount} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPersonAmount(String personAmount) {
        descriptor.setPersonAmount(new PersonAmount(personAmount));
        return this;
    }

    /**
     * build an editpersondescriptor
     * @return editpersondescriptor
     */
    public EditPersonCommand.EditPersonDescriptor build() {
        return descriptor;
    }
}
