package woofareyou.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import woofareyou.logic.commands.EditCommand;
import woofareyou.logic.commands.EditCommand.EditPetDescriptor;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;


/**
 * A utility class to help with building EditPetDescriptor objects.
 */
public class EditPetDescriptorBuilder {

    private EditPetDescriptor descriptor;

    public EditPetDescriptorBuilder() {
        descriptor = new EditCommand.EditPetDescriptor();
    }

    public EditPetDescriptorBuilder(EditCommand.EditPetDescriptor descriptor) {
        this.descriptor = new EditPetDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPetDescriptor} with fields containing {@code pet}'s details
     */
    public EditPetDescriptorBuilder(Pet pet) {
        descriptor = new EditPetDescriptor();
        descriptor.setName(pet.getName());
        descriptor.setPhone(pet.getPhone());
        descriptor.setOwnerName(pet.getOwnerName());
        descriptor.setAddress(pet.getAddress());
        descriptor.setTags(pet.getTags());
        descriptor.setDiet(pet.getDiet());
        descriptor.setAppointment(pet.getAppointment());
    }

    /**
     * Sets the {@code Name} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code OwnerName} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withOwnerName(String ownerName) {
        descriptor.setOwnerName(new OwnerName(ownerName));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPetDescriptor} that we are building.
     */
    public EditPetDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPetDescriptor}
     * that we are building.
     */
    public EditPetDescriptorBuilder withTag(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditPetDescriptor build() {
        return descriptor;
    }
}
