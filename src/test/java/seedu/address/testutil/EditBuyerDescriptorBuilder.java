package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditBuyerCommand.EditBuyerDescriptor;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditBuyerDescriptor objects.
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
     * Returns an {@code EditBuyerDescriptor} with fields containing {@code buyer}'s details
     */
    public EditBuyerDescriptorBuilder(Buyer buyer) {
        descriptor = new EditBuyerDescriptor();
        descriptor.setName(buyer.getName());
        descriptor.setPhone(buyer.getPhone());
        descriptor.setTags(buyer.getTags());
        descriptor.setAppointment(buyer.getAppointment());
        descriptor.setPropertyToBuy(buyer.getPropertyToBuy());
        descriptor.setHouseType(buyer.getPropertyToBuy().getHouse().getHouseType());
        descriptor.setLocation(buyer.getPropertyToBuy().getHouse().getLocation());
        descriptor.setPriceRange(buyer.getPropertyToBuy().getPriceRange());
    }

    /**
     * Sets the {@code Name} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withAppointment(String appointment) {
        descriptor.setAppointment(new Appointment(appointment));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBuyerDescriptor}
     * that we are building.
     */
    public EditBuyerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code HouseType} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withHouseType(String houseType) {
        descriptor.setHouseType(HouseType.getHouseType(houseType));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code EditBuyerDescriptor} that we are building.
     */
    public EditBuyerDescriptorBuilder withPriceRange(int lower, int upper) {
        descriptor.setPriceRange(new PriceRange(lower, upper));
        return this;
    }

    public EditBuyerDescriptor build() {
        return descriptor;
    }
}
