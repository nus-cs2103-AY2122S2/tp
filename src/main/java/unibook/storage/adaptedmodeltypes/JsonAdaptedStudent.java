package unibook.storage.adaptedmodeltypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.exceptions.GroupNotFoundException;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.model.person.Student;
import unibook.model.tag.Tag;

public class JsonAdaptedStudent extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Students's %s field is missing!";
    public static final String GROUP_NOT_FOUND_MESSAGE_FORMAT = "Group \"%s\" does not exist in UniBook";
    private Set<JsonAdaptedGroupCode> groups;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("tagged") Set<JsonAdaptedTag> tagged,
                              @JsonProperty("modules") Set<JsonAdaptedModuleCode> modules,
                              @JsonProperty("groups") Set<JsonAdaptedGroupCode> groups) {
        super(name, phone, email, tagged, modules);
        this.groups = groups;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        super(source);
        this.groups = new HashSet<>();
        //construct the json group code object for each group the student is in
        for (Group group : source.getGroups()) {
            this.groups.add(new JsonAdaptedGroupCode(new JsonAdaptedModuleCode(group.getModule().getModuleCode()),
                group.getGroupName()));
        }
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    @Override
    public Student toModelType(UniBook uniBook) throws IllegalValueException {
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
            try {
                moduleObjs.add(uniBook.getModuleByCode(moduleCode.toModelType()));
            } catch (ModuleNotFoundException m) {
                throw new IllegalValueException(String.format(MODULE_DOES_NOT_EXIST_MESSAGE, moduleCode));
            }
        }

        HashSet<Group> groupObjs = new HashSet<>();
        for (JsonAdaptedGroupCode grpCode : groups) {
            try {
                Module moduleOfGroup = uniBook.getModuleByCode(grpCode.getModuleCode().toModelType());
                Group group = moduleOfGroup.getGroupByName(grpCode.getGroupName());
                groupObjs.add(group);
            } catch (ModuleNotFoundException m) {
                throw new IllegalValueException(String.format(MODULE_DOES_NOT_EXIST_MESSAGE, grpCode.getModuleCode()));
            } catch (GroupNotFoundException g) {
                throw new IllegalValueException(String.format(GROUP_NOT_FOUND_MESSAGE_FORMAT, grpCode.getGroupName()));
            }
        }

        return new Student(modelName, modelPhone, modelEmail, modelTags, moduleObjs, groupObjs);
    }
}
