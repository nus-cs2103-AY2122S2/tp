package seedu.trackbeau.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Customer}.
 */
class JsonAdaptedCustomer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String skinType;
    private final String hairType;
    private final List<JsonAdaptedTag> staffs = new ArrayList<>();
    private final List<JsonAdaptedTag> services = new ArrayList<>();
    private final List<JsonAdaptedTag> allergies = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("skinType") String skinType,
                               @JsonProperty("hairType") String hairType,
                               @JsonProperty("staffs") List<JsonAdaptedTag> staffs,
                               @JsonProperty("services") List<JsonAdaptedTag> services,
                               @JsonProperty("allergies") List<JsonAdaptedTag> allergies) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skinType = skinType;
        this.hairType = hairType;
        if (staffs != null) {
            this.staffs.addAll(staffs);
        }
        if (services != null) {
            this.services.addAll(services);
        }
        if (allergies != null) {
            this.allergies.addAll(allergies);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        skinType = source.getSkinType().value;
        hairType = source.getHairType().value;
        staffs.addAll(source.getStaffs().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        services.addAll(source.getServices().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        allergies.addAll(source.getAllergies().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {
        final List<Tag> staffTags = new ArrayList<>();
        for (JsonAdaptedTag staff : staffs) {
            staffTags.add(staff.toModelType());
        }
        final Set<Tag> modelStaffs = new HashSet<>(staffTags);

        final List<Tag> serviceTags = new ArrayList<>();
        for (JsonAdaptedTag service : services) {
            serviceTags.add(service.toModelType());
        }
        final Set<Tag> modelServices = new HashSet<>(serviceTags);

        final List<Tag> allergyTags = new ArrayList<>();
        for (JsonAdaptedTag allergy : allergies) {
            allergyTags.add(allergy.toModelType());
        }
        final Set<Tag> modelAllergies = new HashSet<>(allergyTags);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }

        final Address modelAddress = new Address(address);

        if (skinType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SkinType.class.getSimpleName()));
        }
        if (!SkinType.isValidSkinType(skinType)) {
            throw new IllegalValueException(SkinType.MESSAGE_CONSTRAINTS);
        }

        final SkinType modelSkinType = new SkinType(skinType);

        if (hairType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HairType.class.getSimpleName()));
        }
        if (!HairType.isValidHairType(hairType)) {
            throw new IllegalValueException(HairType.MESSAGE_CONSTRAINTS);
        }

        final HairType modelHairType = new HairType(hairType);

        return new Customer(modelName, modelPhone, modelEmail,
                modelAddress, modelSkinType, modelHairType, modelStaffs, modelServices, modelAllergies);
    }

}
