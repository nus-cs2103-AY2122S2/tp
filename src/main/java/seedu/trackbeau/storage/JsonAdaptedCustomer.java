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
import seedu.trackbeau.model.customer.Birthdate;
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
    private final String birthDate;
    private final List<JsonAdaptedTag> staffs = new ArrayList<>();
    private final List<JsonAdaptedTag> services = new ArrayList<>();
    private final List<JsonAdaptedTag> allergies = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCustomer} with the given customer details.
     */
    @JsonCreator
    public JsonAdaptedCustomer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email, @JsonProperty("address") String address,
                               @JsonProperty("skinType") String skinType,
                               @JsonProperty("hairType") String hairType,
                               @JsonProperty("staffs") List<JsonAdaptedTag> staffs,
                               @JsonProperty("services") List<JsonAdaptedTag> services,
                               @JsonProperty("allergies") List<JsonAdaptedTag> allergies,
                               @JsonProperty("birthdate") String birthDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.skinType = skinType;
        this.hairType = hairType;
        this.birthDate = birthDate;
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
     * Converts a given {@code Customer} into this class for Jackson use.
     */
    public JsonAdaptedCustomer(Customer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        skinType = source.getSkinType().value;
        hairType = source.getHairType().value;
        birthDate = source.getBirthdate().toString();
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

    Set<Tag> getModelStaffs() throws IllegalValueException {
        final List<Tag> staffTags = new ArrayList<>();
        for (JsonAdaptedTag staff : staffs) {
            staffTags.add(staff.toModelType());
        }
        return new HashSet<>(staffTags);
    }

    Set<Tag> getModelServices() throws IllegalValueException {
        final List<Tag> serviceTags = new ArrayList<>();
        for (JsonAdaptedTag service : services) {
            serviceTags.add(service.toModelType());
        }
        return new HashSet<>(serviceTags);
    }

    Set<Tag> getModelAllergies() throws IllegalValueException {
        final List<Tag> allergyTags = new ArrayList<>();
        for (JsonAdaptedTag allergy : allergies) {
            allergyTags.add(allergy.toModelType());
        }
        return new HashSet<>(allergyTags);
    }

    Name getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    Phone getModelPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(phone);
    }

    Email getModelEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(email);
    }

    Address getModelAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    SkinType getModelSkinType() throws IllegalValueException {
        if (skinType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SkinType.class.getSimpleName()));
        }
        if (!SkinType.isValidSkinType(skinType)) {
            throw new IllegalValueException(SkinType.MESSAGE_CONSTRAINTS);
        }
        return new SkinType(skinType);
    }

    HairType getModelHairType() throws IllegalValueException {
        if (hairType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    HairType.class.getSimpleName()));
        }
        if (!HairType.isValidHairType(hairType)) {
            throw new IllegalValueException(HairType.MESSAGE_CONSTRAINTS);
        }
        return new HairType(hairType);
    }

    Birthdate getModelBirthdate() throws IllegalValueException {
        if (birthDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Birthdate.class.getSimpleName()));
        }
        if (!Birthdate.isValidBirthdate(birthDate)) {
            throw new IllegalValueException(Birthdate.MESSAGE_CONSTRAINTS);
        }
        return new Birthdate(birthDate);
    }

    /**
     * Converts this Jackson-friendly adapted customer object into the model's {@code Customer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted customer.
     */
    public Customer toModelType() throws IllegalValueException {
        final Set<Tag> modelStaffs = this.getModelStaffs();
        final Set<Tag> modelServices = this.getModelServices();
        final Set<Tag> modelAllergies = this.getModelAllergies();
        final Name modelName = this.getModelName();
        final Phone modelPhone = this.getModelPhone();
        final Email modelEmail = this.getModelEmail();
        final Address modelAddress = this.getModelAddress();
        final SkinType modelSkinType = this.getModelSkinType();
        final HairType modelHairType = this.getModelHairType();
        final Birthdate modelBirthdate = this.getModelBirthdate();
        return new Customer(modelName, modelPhone, modelEmail,
                modelAddress, modelSkinType, modelHairType,
                modelStaffs, modelServices, modelAllergies, modelBirthdate);
    }

}
