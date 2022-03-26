package unibook.storage.adaptedmodeltypes;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.group.Group;
import unibook.model.person.Student;

public class JsonAdaptedStudent extends JsonAdaptedPerson {
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
     * Returns the Set of {@code Group} objects to use for the converted model.
     * Performs checks on the validity of the field in the Json before returning.
     *
     * @param uniBook reference to UniBook instance in group, to check if group with given code exists.
     * @throws IllegalValueException if any module is invalid (invalid name, or group does not exist in UniBook).
     */
    public Set<Group> getModelGroups(UniBook uniBook) throws IllegalValueException {
        Set<Group> modelGroups = new HashSet<>();
        for (JsonAdaptedGroupCode groupCode : groups) {
            modelGroups.add(groupCode.toModelType(uniBook));
        }
        return modelGroups;
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    @Override
    public Student toModelType(UniBook uniBook) throws IllegalValueException {
        return new Student(getModelName(), getModelPhone(), getModelEmail(),
            getModelTags(), getModelModules(uniBook), getModelGroups(uniBook));
    }
}
