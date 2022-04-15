package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.entry.Address;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Email;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "shopSG";
    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_EMAIL = "amy@shopSG.com";
    public static final String DEFAULT_ADDRESS = "21 Jurong Street";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     */
    public CompanyBuilder(Company companyToCopy) {
        name = companyToCopy.getName();
        phone = companyToCopy.getPhone();
        email = companyToCopy.getEmail();
        address = companyToCopy.getAddress();
        tags = new HashSet<>(companyToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Company} that we are building.
     */
    public CompanyBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Company} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    public Company build() {
        return new Company(name, phone, email, address, tags);
    }

}
