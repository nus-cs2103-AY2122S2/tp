package seedu.trackbeau.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;
import seedu.trackbeau.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_SKIN_TYPE = "Normal";
    private static final String DEFAULT_HAIR_TYPE = "Normal";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private SkinType skinType;
    private HairType hairType;
    private Set<Tag> staffs;
    private Set<Tag> services;
    private Set<Tag> allergies;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        skinType = new SkinType(DEFAULT_SKIN_TYPE);
        hairType = new HairType(DEFAULT_HAIR_TYPE);
        staffs = new HashSet<>();
        services = new HashSet<>();
        allergies = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        address = customerToCopy.getAddress();
        skinType = customerToCopy.getSkinType();
        hairType = customerToCopy.getHairType();
        staffs = new HashSet<>(customerToCopy.getStaffs());
        services = new HashSet<>(customerToCopy.getServices());
        allergies = new HashSet<>(customerToCopy.getAllergies());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code staffs} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withStaffs(String ... staffs) {
        this.staffs = SampleDataUtil.getTagSet(staffs);
        return this;
    }

    /**
     * Parses the {@code services} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withServices(String ... services) {
        this.services = SampleDataUtil.getTagSet(services);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getTagSet(allergies);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Skin Type} of the {@code Person} that we are building.
     */
    public PersonBuilder withSkinType(String skinType) {
        this.skinType = new SkinType(skinType);
        return this;
    }

    /**
     * Sets the {@code Hair Type} of the {@code Person} that we are building.
     */
    public PersonBuilder withHairType(String hairType) {
        this.hairType = new HairType(hairType);
        return this;
    }

    public Customer build() {
        return new Customer(name, phone, email, address, skinType, hairType, staffs, services, allergies);
    }

}
