package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;
/**
 * A utility class to help with building EditclientDescriptor objects.
 */
public class EditSellerDescriptorBuilder {

    private EditSellerDescriptor descriptor;

    public EditSellerDescriptorBuilder() {
        descriptor = new EditSellerDescriptor();
    }

    public EditSellerDescriptorBuilder(EditSellerDescriptor descriptor) {
        this.descriptor = new EditSellerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditclientDescriptor} with fields containing {@code buyer}'s details
     */
    public EditSellerDescriptorBuilder(Seller buyer) {
        descriptor = new EditSellerDescriptor();
        descriptor.setName(buyer.getName());
        descriptor.setPhone(buyer.getPhone());
        descriptor.setTags(buyer.getTags());
        descriptor.setAppointment(buyer.getAppointment());
    }

    /**
     * Sets the {@code Name} of the {@code EditclientDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code EditclientDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code EditclientDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withAppointment(String appointment) {
        descriptor.setAppointment(new Appointment(appointment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditclientDescriptor}
     * that we are building.
     */
    public EditSellerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditSellerDescriptor build() {
        return descriptor;
    }
}
