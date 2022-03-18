package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Preference;
import seedu.address.model.person.UserType;
import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.Region;
import seedu.address.model.property.Size;
/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setFavourite(person.getFavourite());
        descriptor.setAddress(person.getAddress());
        if (person.getPreference().isPresent()) {
            descriptor.setPreference(person.getPreference().get());
            descriptor.clearProperties();
        }
        if (!person.getProperties().isEmpty()) {
            descriptor.setProperties(person.getProperties());
        }
        descriptor.setUserType(person.getUserType());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Favourite} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFavourite(Favourite favourite) {
        descriptor.setFavourite(favourite);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code userType} into a {@code UserType} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     * @param userType
     */
    public EditPersonDescriptorBuilder withUserType(String userType) {
        descriptor.setUserType(new UserType(userType));
        return this;
    }

    /**
     * Parses the (@code preference} into a {@code Preference} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     * @param preference
     */
    public EditPersonDescriptorBuilder withPreference(String preference) {
        String[] splitPreference = preference.split(",");
        Region region = Region.fromString(splitPreference[0]);
        Size size = Size.fromString(splitPreference[1]);
        Price lowPrice = new Price(splitPreference[2]);
        Price highPrice = new Price(splitPreference[3]);

        Preference editedPreference = new Preference(region, size, lowPrice, highPrice);

        descriptor.setPreference(editedPreference);
        return this;
    }

    /**
     * Parses the {@code properties} into a {@code Set<Property>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withProperties(String... properties) {
        Set<Property> propertySet = new HashSet<>();

        for (String s : properties) {
            String[] propertySplit = s.split(",");
            Region region = Region.fromString(propertySplit[0]);
            Address address = new Address(propertySplit[1]);
            Size size = Size.fromString(propertySplit[2]);
            Price price = new Price(propertySplit[3]);
            propertySet.add(new Property(region, address, size, price));
        }

        descriptor.setProperties(propertySet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
