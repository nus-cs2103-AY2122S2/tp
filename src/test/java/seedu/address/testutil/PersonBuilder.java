package seedu.address.testutil;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.userimage.UserImage;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_USERTYPE = "buyer";
    public static final String DEFAULT_PREFERENCE = "West,4-room,$100000,$200000";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Property> properties;
    private Favourite favourite;
    private Optional<Preference> preference;
    private UserType userType;
    private Set<UserImage> userImages;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        favourite = new Favourite(false);
        address = new Address(DEFAULT_ADDRESS);
        properties = new HashSet<>();
        preference = createPreference(DEFAULT_PREFERENCE);
        userType = new UserType(DEFAULT_USERTYPE);
        userImages = new LinkedHashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        favourite = personToCopy.getFavourite();
        address = personToCopy.getAddress();
        properties = personToCopy.getProperties();
        preference = personToCopy.getPreference();
        userType = personToCopy.getUserType();
        userImages = personToCopy.getUserImages();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code userType} into a {@code UserType} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withUserType(String userType) {
        this.userType = new UserType(userType);
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
     * Sets the {@code Favourite} of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(boolean status) {
        this.favourite = new Favourite(status);
        return this;
    }

    /**
     * Sets the {@code Property} of the {@code Person} that we are building.
     */
    public PersonBuilder withProperties(String properties) {
        String[] propertySplit = properties.split(",");

        Region region = Region.fromString(propertySplit[0]);
        Address address = new Address(propertySplit[1]);
        Size size = Size.fromString(propertySplit[2]);
        Price price = new Price(propertySplit[3]);

        this.properties.add(new Property(region, address, size, price));
        return this;
    }

    /**
     * Sets the {@code Preference} of the {@code Person} that we are building.
     */
    public PersonBuilder withPreference(String preference) {
        this.preference = createPreference(preference);
        return this;
    }

    /**
     * Does not set the {@code Preference} of the {@code Person} that we are building & return the current Person.
     */
    public PersonBuilder withPreference() {
        this.preference = null;
        return this;
    }

    /**
     * Creates a {@code Preference} from the given {@code preference}
     */
    private Optional<Preference> createPreference(String preference) {
        String[] preferenceSplit = preference.split(",");

        Region region = Region.fromString(preferenceSplit[0]);
        Size size = Size.fromString(preferenceSplit[1]);
        Price lowPrice = new Price(preferenceSplit[2]);
        Price highPrice = new Price(preferenceSplit[3]);

        Preference createdPref = new Preference(region, size, lowPrice, highPrice);

        return Optional.of(createdPref);
    }

    public Person build() {
        return new Person(name, phone, email, favourite, address, properties, preference, userType, userImages);
    }

}
