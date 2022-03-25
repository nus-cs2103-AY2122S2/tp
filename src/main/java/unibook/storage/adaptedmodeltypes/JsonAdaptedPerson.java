package unibook.storage.adaptedmodeltypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonAdaptedProfessor.class, name = "Professor"),
    @JsonSubTypes.Type(value = JsonAdaptedStudent.class, name = "Student")
})
public abstract class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String MODULE_DOES_NOT_EXIST_MESSAGE =
        "Module with code %s that this person is associated with does not exist not in unibook!";

    protected final String name;
    protected final String phone;
    protected final String email;
    protected final List<JsonAdaptedTag> tagged = new ArrayList<>();
    protected final Set<JsonAdaptedModuleCode> modules = new HashSet<>();
    protected final Set<JsonAdaptedGroupCode> groups = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("tagged") Set<JsonAdaptedTag> tagged,
                             @JsonProperty("modules") Set<JsonAdaptedModuleCode> modules) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (modules != null) {
            this.modules.addAll(modules);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toSet()));
        modules.addAll(source.getModules().stream()
            .map(module -> module.getModuleCode())
            .map(JsonAdaptedModuleCode::new)
            .collect(Collectors.toSet()));
    }

    /**
     * Abstract method.
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @param unibook unibook application main object
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public abstract Person toModelType(UniBook unibook) throws IllegalValueException, ModuleNotFoundException;

    /**
     * Returns the {@code Name} object to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @throws IllegalValueException if name string being parsed is null or invalid.
     */
    public Name getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        return modelName;
    }

    /**
     * Returns the {@code Email} object to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @throws IllegalValueException if email string being parsed is null or invalid.
     */
    public Email getModelEmail() throws IllegalValueException {
        Email modelEmail;
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        } else if (email.isEmpty()) {
            modelEmail = new Email();
        } else if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        } else {
            modelEmail = new Email(email);
        }
        return modelEmail;
    }

    /**
     * Returns the {@code Phone} object to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @throws IllegalValueException if phone string being parsed is null or invalid.
     */
    public Phone getModelPhone() throws IllegalValueException {
        Phone modelPhone;
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        } else if (phone.isEmpty()) {
            modelPhone = new Phone();
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = new Phone(phone);
        }
        return modelPhone;
    }

    /**
     * Returns the Set of {@code Tag} objects to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @throws IllegalValueException if any tag is invalid.
     */
    public Set<Tag> getModelTags() throws IllegalValueException {
        final Set<Tag> personTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        return personTags;
    }

    /**
     * Returns the Set of {@code Module} objects to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @param uniBook reference to UniBook instance in model, to check if module with given code exists.
     * @throws IllegalValueException if any module is invalid (invalid name, or module does not exist in UniBook).
     */
    public Set<Module> getModelModules(UniBook uniBook) throws IllegalValueException {
        final Set<Module> moduleObjs = new HashSet<>();
        //check if the given modules exist in unibook
        //throw IllegalValueException if not the case
        for (JsonAdaptedModuleCode moduleCode : modules) {
            try {
                moduleObjs.add(uniBook.getModuleByCode(moduleCode.toModelType()));
            } catch (ModuleNotFoundException m) {
                throw new IllegalValueException(String.format(MODULE_DOES_NOT_EXIST_MESSAGE, moduleCode));
            }
        }
        return moduleObjs;
    }
}
