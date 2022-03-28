package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.TAssist;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

/**
 * An Immutable TAssist that is serializable to JSON format.
 */
@JsonRootName(value = "tassist")
class JsonSerializableTAssist {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate students(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate modules(s).";
    public static final String MESSAGE_DUPLICATE_CLASS_GROUP = "Class groups list contains duplicate classes(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTaModule> modules = new ArrayList<>();
    private final List<JsonAdaptedClassGroup> classGroups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTAssist} with the given TAssist entities.
     */
    @JsonCreator
    public JsonSerializableTAssist(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                   @JsonProperty("modules") List<JsonAdaptedTaModule> modules,
                                   @JsonProperty("classGroups") List<JsonAdaptedClassGroup> classGroups) {
        this.students.addAll(students);
        this.modules.addAll(modules);
        this.classGroups.addAll(classGroups);
    }

    /**
     * Converts a given {@code ReadOnlyTAssist} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTAssist}.
     */
    public JsonSerializableTAssist(ReadOnlyTAssist source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedTaModule::new).collect(Collectors.toList()));
        classGroups.addAll(source.getClassGroupList().stream().map(JsonAdaptedClassGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this TAssist into the model's {@code TAssist} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TAssist toModelType() throws IllegalValueException {
        TAssist tAssist = new TAssist();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (tAssist.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            tAssist.addStudent(student);
        }

        for (JsonAdaptedTaModule jsonAdaptedTaModule : modules) {
            TaModule module = jsonAdaptedTaModule.toModelType(tAssist.getStudentList());
            if (tAssist.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            tAssist.addModule(module);
        }

        for (JsonAdaptedClassGroup jsonAdaptedClassGroup : classGroups) {
            ClassGroup classGroup = jsonAdaptedClassGroup.toModelType(
                    tAssist.getModuleList(), tAssist.getStudentList());
            if (tAssist.hasClassGroup(classGroup)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLASS_GROUP);
            }
            tAssist.addClassGroup(classGroup);
        }

        return tAssist;
    }

}
