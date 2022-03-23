package unibook.storage.adaptedmodeltypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.Module;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Office;
import unibook.model.person.Phone;
import unibook.model.person.Professor;
import unibook.model.tag.Tag;

public class JsonAdaptedProfessor extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Professor's %s field is missing!";
    private final String office;

    /**
     * Constructs a {@code JsonAdaptedProfessor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedProfessor(@JsonProperty("name") String name,
                                @JsonProperty("phone") String phone,
                                @JsonProperty("email") String email,
                                @JsonProperty("tagged") Set<JsonAdaptedTag> tagged,
                                @JsonProperty("modules") Set<JsonAdaptedModuleCode> modules,
                                @JsonProperty("office") String office) {
        super(name, phone, email, tagged, modules);
        this.office = office;
    }

    /**
     * Converts a given {@code Professor} into this class for Jackson use.
     */
    public JsonAdaptedProfessor(Professor source) {
        super(source);
        office = source.getOffice().value;
    }

    /**
     * Converts this Jackson-friendly adapted professor object into the model's {@code Professor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    @Override
    public Professor toModelType(UniBook uniBook) throws IllegalValueException {
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
        //throw IllegalValueException if not the case
        for (JsonAdaptedModuleCode moduleCode : modules) {
            try {
                moduleObjs.add(uniBook.getModuleByCode(moduleCode.toModelType()));
            } catch (ModuleNotFoundException m) {
                throw new IllegalValueException(String.format(MODULE_DOES_NOT_EXIST_MESSAGE, moduleCode));
            }
        }
        final Office officeObj = new Office(office);

        return new Professor(modelName, modelPhone, modelEmail, modelTags, officeObj, moduleObjs);
    }

}
