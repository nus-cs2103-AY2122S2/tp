package seedu.trackbeau.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.trackbeau.logic.commands.EditCommand;
import seedu.trackbeau.logic.commands.EditCommand.EditCustomerDescriptor;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * A utility class to help with building EditCustomerDescriptor objects.
 */
public class EditCustomerDescriptorBuilder {

    private EditCommand.EditCustomerDescriptor descriptor;

    public EditCustomerDescriptorBuilder() {
        descriptor = new EditCustomerDescriptor();
    }

    public EditCustomerDescriptorBuilder(EditCustomerDescriptor descriptor) {
        this.descriptor = new EditCustomerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCustomerDescriptor} with fields containing {@code customer}'s details
     */
    public EditCustomerDescriptorBuilder(Customer customer) {
        descriptor = new EditCustomerDescriptor();
        descriptor.setName(customer.getName());
        descriptor.setPhone(customer.getPhone());
        descriptor.setEmail(customer.getEmail());
        descriptor.setAddress(customer.getAddress());
        descriptor.setSkinType(customer.getSkinType());
        descriptor.setHairType(customer.getHairType());
        descriptor.setBirthdate(customer.getBirthdate());
        descriptor.setStaffs(customer.getStaffs());
        descriptor.setServices(customer.getServices());
        descriptor.setAllergies(customer.getAllergies());
    }

    /**
     * Sets the {@code Name} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Skin Type} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withSkinType(String skinType) {
        descriptor.setSkinType(new SkinType(skinType));
        return this;
    }

    /**
     * Sets the {@code Hair Type} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withHairType(String hairType) {
        descriptor.setHairType(new HairType(hairType));
        return this;
    }

    /**
     * Sets the {@code Birthdate} of the {@code EditCustomerDescriptor} that we are building.
     */
    public EditCustomerDescriptorBuilder withBirthdate(String birthdate) {
        descriptor.setBirthdate(new Birthdate(birthdate));
        return this;
    }

    /**
     * Parses the {@code staffs} into a {@code Set<Tag>} and set it to the {@code EditCustomerDescriptor}
     * that we are building.
     */
    public EditCustomerDescriptorBuilder withStaffs(String... staffs) {
        Set<Tag> tagSet = Stream.of(staffs).map(Tag::new).collect(Collectors.toSet());
        descriptor.setStaffs(tagSet);
        return this;
    }

    /**
     * Parses the {@code services} into a {@code Set<Tag>} and set it to the {@code EditCustomerDescriptor}
     * that we are building.
     */
    public EditCustomerDescriptorBuilder withServices(String... services) {
        Set<Tag> tagSet = Stream.of(services).map(Tag::new).collect(Collectors.toSet());
        descriptor.setServices(tagSet);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Tag>} and set it to the {@code EditCustomerDescriptor}
     * that we are building.
     */
    public EditCustomerDescriptorBuilder withAllergies(String... allergies) {
        Set<Tag> tagSet = Stream.of(allergies).map(Tag::new).collect(Collectors.toSet());
        descriptor.setAllergies(tagSet);
        return this;
    }

    public EditCustomerDescriptor build() {
        return descriptor;
    }
}
