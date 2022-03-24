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
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.person.Person;

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

}
