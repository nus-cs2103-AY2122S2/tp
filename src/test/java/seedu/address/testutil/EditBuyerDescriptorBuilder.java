package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditclientDescriptor objects.
 */
public class EditBuyerDescriptorBuilder {

    private EditBuyerDescriptor descriptor;

    public EditBuyerDescriptorBuilder() {
        descriptor = new EditBuyerDescriptor();
    }

    public EditBuyerDescriptorBuilder(EditBuyerDescriptor descriptor) {
        this.descriptor = new EditBuyerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditclientDescriptor} with fields containing {@code buyer}'s details
     */
    public EditBuyerDescriptorBuilder(Buyer buyer) {
        descriptor = new EditBuyerDescriptor();
        descriptor.setName(buyer.getName());
        descriptor.setPhone(buyer.getPhone());
        descriptor.setTags(buyer.getTags());
        descriptor.setAppointment(buyer.getAppointment());
    }

    /**
     * Sets the {@code Name} of the {@code EditclientDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code EditclientDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code EditclientDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withAppointment(String appointment) {
        descriptor.setAppointment(new Appointment(appointment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditclientDescriptor}
     * that we are building.
     */
    public EditBuyerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditBuyerDescriptor build() {
        return descriptor;
    }
}
