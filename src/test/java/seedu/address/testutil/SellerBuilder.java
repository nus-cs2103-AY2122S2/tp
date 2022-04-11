package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.client.Appointment;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.property.Address;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building client objects.
 */
public class SellerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_APPOINTMENT = "2022-05-01-12-00";
    public static final PropertyToSell DEFAULT_PROPERTY = new PropertyToSell(
            new House(HouseType.BUNGALOW, new Location("bishan")),
            new PriceRange(10, 20),
            new Address("Default address")
    );

    private Name name;
    private Phone phone;
    private Set<Tag> tags;
    private Appointment appointment;
    private PropertyToSell propertyToSell;

    /**
     * Creates a {@code sellerBuilder} with the default details.
     */
    public SellerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        appointment = new Appointment(DEFAULT_APPOINTMENT);
        tags = new HashSet<>();
        propertyToSell = DEFAULT_PROPERTY;
    }

    /**
     * Initializes the clientBuilder with the data of {@code clientToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        name = sellerToCopy.getName();
        phone = sellerToCopy.getPhone();
        appointment = sellerToCopy.getAppointment();
        tags = new HashSet<>(sellerToCopy.getTags());
        propertyToSell = sellerToCopy.getPropertyToSell();
    }

    /**
     * Sets the {@code Name} of the {@code client} that we are building.
     */
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code client} that we are building.
     */
    public SellerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code client} that we are building.
     */
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code client} that we are building.
     */
    public SellerBuilder withAppointment(String appointment) {
        this.appointment = new Appointment(appointment);
        return this;
    }

    /**
     * Sets the {@code Properties} of the {@code buyer} that we are building.
     */
    public SellerBuilder withProperty(PropertyToSell property) {
        this.propertyToSell = property;
        return this;
    }

    public Seller build() {
        return new Seller(name, phone, appointment, tags, propertyToSell);
    }

}
