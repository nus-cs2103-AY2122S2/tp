package seedu.trackbeau.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Birthdate;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.RegistrationDate;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;
import seedu.trackbeau.model.util.SampleDataUtil;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    private static final String DEFAULT_SKIN_TYPE = "Normal";
    private static final String DEFAULT_HAIR_TYPE = "Normal";
    private static final String DEFAULT_BIRTHDATE = "07-12-2001";
    private static final String DEFAULT_REGDATE = "23-03-2022";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthdate birthdate;
    private RegistrationDate regDate;
    private SkinType skinType;
    private HairType hairType;
    private Set<Tag> staffs;
    private Set<Tag> services;
    private Set<Tag> allergies;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        skinType = new SkinType(DEFAULT_SKIN_TYPE);
        hairType = new HairType(DEFAULT_HAIR_TYPE);
        birthdate = new Birthdate(DEFAULT_BIRTHDATE);
        regDate = new RegistrationDate(DEFAULT_REGDATE);
        staffs = new HashSet<>();
        services = new HashSet<>();
        allergies = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        address = customerToCopy.getAddress();
        skinType = customerToCopy.getSkinType();
        hairType = customerToCopy.getHairType();
        birthdate = customerToCopy.getBirthdate();
        regDate = customerToCopy.getRegDate();
        staffs = new HashSet<>(customerToCopy.getStaffs());
        services = new HashSet<>(customerToCopy.getServices());
        allergies = new HashSet<>(customerToCopy.getAllergies());
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code staffs} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withStaffs(String ... staffs) {
        this.staffs = SampleDataUtil.getTagSet(staffs);
        return this;
    }

    /**
     * Parses the {@code services} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withServices(String ... services) {
        this.services = SampleDataUtil.getTagSet(services);
        return this;
    }

    /**
     * Parses the {@code allergies} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withAllergies(String ... allergies) {
        this.allergies = SampleDataUtil.getTagSet(allergies);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Skin Type} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withSkinType(String skinType) {
        this.skinType = new SkinType(skinType);
        return this;
    }

    /**
     * Sets the {@code Hair Type} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withHairType(String hairType) {
        this.hairType = new HairType(hairType);
        return this;
    }

    /**
     * Sets the {@code Birthdate} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withBirthdate(String birthdate) {
        this.birthdate = new Birthdate(birthdate);
        return this;
    }

    /**
     * Sets the {@code RegistrationDate} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withRegistrationDate(String regDate) {
        this.regDate = new RegistrationDate(regDate);
        return this;
    }


    /**
     * Builds a customer.
     */
    public Customer build() {
        return new Customer(name, phone, email, address, skinType,
                hairType, staffs, services, allergies, birthdate, regDate);
    }

}
