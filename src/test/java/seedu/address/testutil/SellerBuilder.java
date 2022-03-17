package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.client.Address;
import seedu.address.model.client.Appointment;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Remark;
import seedu.address.model.property.PropertyToSell;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building client objects.
 */
public class SellerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DESCRIPTION = "Amy description";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REMARK = "Amy remark";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_APPOINTMENT = "2022-05-01-12-00";

    private Name name;
    private Description description;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;
    private Set<Tag> tags;
    private Appointment appointment;
    private List<PropertyToSell> properties;

    /**
     * Creates a {@code clientBuilder} with the default details.
     */
    public SellerBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        appointment = new Appointment(DEFAULT_APPOINTMENT);
        tags = new HashSet<>();
        properties = new ArrayList<>();
    }

    /**
     * Initializes the clientBuilder with the data of {@code clientToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        name = sellerToCopy.getName();
        description = sellerToCopy.getDescription();
        phone = sellerToCopy.getPhone();
        email = sellerToCopy.getEmail();
        address = sellerToCopy.getAddress();
        remark = sellerToCopy.getRemark();
        appointment = sellerToCopy.getAppointment();
        tags = new HashSet<>(sellerToCopy.getTags());
        properties = new ArrayList<>(sellerToCopy.getPropertiesToSell());
    }

    /**
     * Sets the {@code Name} of the {@code client} that we are building.
     */
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code client} that we are building.
     */
    public SellerBuilder withDescription(String description) {
        this.description = new Description(description);
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
     * Sets the {@code Address} of the {@code client} that we are building.
     */
    public SellerBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code Email} of the {@code client} that we are building.
     */
    public SellerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code client} that we are building.
     */
    public SellerBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
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
     * Sets the {@code Properties} of the {@code seller} that we are building.
     */
    public SellerBuilder withProperties(PropertyToSell... properties) {
        this.properties = new ArrayList<>(Arrays.asList(properties));
        return this;
    }

    public Seller build() {
        return new Seller(name, description, phone, email, address, remark, appointment, tags, properties);
    }

}
