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
public class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String MODULE_DOES_NOT_EXIST_MESSAGE =
        "Module this person is associated with is not in unibook!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final Set<JsonAdaptedModuleCode> modules = new HashSet<>();
    private final Set<JsonAdaptedGroupCode> groups = new HashSet<>();

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
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @param unibook unibook application main object
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType(UniBook unibook) throws IllegalValueException, ModuleNotFoundException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Module> moduleObjs = new HashSet<>();

        //check if the given modules exist in unibook
        //throw exception if not the case
        for (JsonAdaptedModuleCode moduleCode : modules) {
            moduleObjs.add(unibook.getModuleByCode(moduleCode.toModelType()));
        }

        return new Person(modelName, modelPhone, modelEmail, modelTags, moduleObjs);
    }

}
