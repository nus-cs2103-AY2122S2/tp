package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.Address;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;
/**
 * A utility class to help with building EditSellerDescriptor objects.
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
     * Returns an {@code EditSellerDescriptor} with fields containing {@code seller}'s details
     */
    public EditSellerDescriptorBuilder(Seller seller) {
        descriptor = new EditSellerDescriptor();
        descriptor.setName(seller.getName());
        descriptor.setPhone(seller.getPhone());
        descriptor.setTags(seller.getTags());
        descriptor.setAppointment(seller.getAppointment());
        descriptor.setPropertyToSell(seller.getPropertyToSell());
        descriptor.setHouseType(seller.getPropertyToSell().getHouse().getHouseType());
        descriptor.setLocation(seller.getPropertyToSell().getHouse().getLocation());
        descriptor.setPriceRange(seller.getPropertyToSell().getPriceRange());
        descriptor.setAddress(seller.getAddress());
    }

    /**
     * Sets the {@code Name} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withAppointment(String appointment) {
        descriptor.setAppointment(new Appointment(appointment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSellerDescriptor}
     * that we are building.
     */
    public EditSellerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code HouseType} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withHouseType(String houseType) {
        descriptor.setHouseType(HouseType.getHouseType(houseType));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Price Range} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPriceRange(int lower, int upper) {
        descriptor.setPriceRange(new PriceRange(lower, upper));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditSellerDescriptor build() {
        return descriptor;
    }
}
