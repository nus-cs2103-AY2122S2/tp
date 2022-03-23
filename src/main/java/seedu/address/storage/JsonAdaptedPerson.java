package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cca;
import seedu.address.model.person.Education;
import seedu.address.model.person.Email;
import seedu.address.model.person.Internship;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final Set<JsonAdaptedTag> ccas = new HashSet<>();
    private final Set<JsonAdaptedTag> educations = new HashSet<>();
    private final Set<JsonAdaptedTag> internships = new HashSet<>();
    private final Set<JsonAdaptedTag> modules = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("educations") List<JsonAdaptedTag> educations,
            @JsonProperty("internships") List<JsonAdaptedTag> internships,
            @JsonProperty("modules") List<JsonAdaptedTag> modules,
            @JsonProperty("ccas") List<JsonAdaptedTag> ccas) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (educations != null) {
            this.educations.addAll(educations);
        }
        if (internships != null) {
            this.internships.addAll(internships);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
        if (ccas != null) {
            this.ccas.addAll(ccas);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        educations.addAll(new HashSet<>(source.getEducations()).stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        internships.addAll(new HashSet<>(source.getInternships()).stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        modules.addAll(new HashSet<>(source.getModules()).stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        ccas.addAll(new HashSet<>(source.getCcas()).stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final Set<Tag> modelCcas = new HashSet<>();
        final Set<Tag> modelEducations = new HashSet<>();
        final Set<Tag> modelInternships = new HashSet<>();
        final Set<Tag> modelModules = new HashSet<>();

        for (JsonAdaptedTag curr : educations) {
            if (!Education.isValidTagName(curr.getTagName())) {
                throw new IllegalValueException(Education.MESSAGE_CONSTRAINTS);
            }
            modelEducations.add(curr.toModelType("education"));
        }

        for (JsonAdaptedTag curr : ccas) {
            if (!Cca.isValidTagName(curr.getTagName())) {
                throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
            }
            modelCcas.add(curr.toModelType("cca"));
        }

        for (JsonAdaptedTag curr : internships) {
            if (!Internship.isValidTagName(curr.getTagName())) {
                throw new IllegalValueException(Internship.MESSAGE_CONSTRAINTS);
            }
            modelInternships.add(curr.toModelType("internship"));
        }

        for (JsonAdaptedTag curr : modules) {
            if (!Module.isValidTagName(curr.getTagName())) {
                throw new IllegalValueException(Module.MESSAGE_CONSTRAINTS);
            }
            modelModules.add(curr.toModelType("module"));
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, new ArrayList<>(modelEducations),
                new ArrayList<>(modelInternships), new ArrayList<>(modelModules), new ArrayList<>(modelCcas));
    }
}
