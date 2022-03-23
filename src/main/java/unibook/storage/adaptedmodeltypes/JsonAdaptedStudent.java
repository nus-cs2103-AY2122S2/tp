package unibook.storage.adaptedmodeltypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.group.Group;
import unibook.model.person.Person;
import unibook.model.person.Student;
import unibook.model.module.Module;

public class JsonAdaptedStudent extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Professor's %s field is missing!";

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
    public Student toModelType(UniBook uniBook) throws IllegalValueException {
        Person person = super.toModelType(uniBook);

        HashSet<Group> groupObjs = new HashSet<>();
        for (JsonAdaptedGroupCode grpCode : groups) {
            Module moduleOfGroup = uniBook.getModuleByCode(grpCode.getModuleCode().toModelType());
            Group group = moduleOfGroup.getGroupByName(grpCode.getGroupName());
            groupObjs.add(group);
        }

        return new Student(person, groupObjs);
    }
}
