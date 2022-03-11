package seedu.trackbeau.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.trackbeau.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code customer}'s details
     */
    public EditPersonDescriptorBuilder(Customer customer) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(customer.getName());
        descriptor.setPhone(customer.getPhone());
        descriptor.setEmail(customer.getEmail());
        descriptor.setAddress(customer.getAddress());
        descriptor.setSkinType(customer.getSkinType());
        descriptor.setHairType(customer.getHairType());
        descriptor.setStaffs(customer.getStaffs());
        descriptor.setServices(customer.getServices());
        descriptor.setAllergies(customer.getAllergies());
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
     * Sets the {@code Skin Type} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSkinType(String skinType) {
        descriptor.setSkinType(new SkinType(skinType));
        return this;
    }

    /**
     * Sets the {@code Hair Type} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withHairType(String hairType) {
        descriptor.setHairType(new HairType(hairType));
        return this;
    }

    /**
     * Parses the {@code staffs} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withStaffs(String... staffs) {
        Set<Tag> tagSet = Stream.of(staffs).map(Tag::new).collect(Collectors.toSet());
        descriptor.setStaffs(tagSet);
        return this;
    }

    /**
     * Parses the {@code services} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withServices(String... services) {
        Set<Tag> tagSet = Stream.of(services).map(Tag::new).collect(Collectors.toSet());
        descriptor.setServices(tagSet);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withAllergies(String... allergies) {
        Set<Tag> tagSet = Stream.of(allergies).map(Tag::new).collect(Collectors.toSet());
        descriptor.setAllergies(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
