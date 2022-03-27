package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Jackson-friendly version of {@link ClassGroup}.
 */
class JsonAdaptedClassGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "ClassGroup's %s field is missing!";
    public static final String NONEXISTENT_MODULE = "Module does not exist!";
    public static final String NONEXISTENT_STUDENT = "Student does not exist!";

    private final String classGroupId;
    private final String classGroupType;
    private final JsonAdaptedTaModule module;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClassGroup} with the given class group details.
     */
    @JsonCreator
    public JsonAdaptedClassGroup(@JsonProperty("classGroupId") String classGroupId,
                                 @JsonProperty("classGroupType") String classGroupType,
                                 @JsonProperty("module") JsonAdaptedTaModule module,
                                 @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.module = module;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code ClassGroup} into this class for Jackson use.
     */
    public JsonAdaptedClassGroup(ClassGroup source) {
        classGroupId = source.getClassGroupId().value;
        classGroupType = source.getClassGroupType().toString();
        module = new JsonAdaptedTaModule(source.getModule());
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted class group object into the model's {@code ClassGroup} object.
     * Checks that the module the class group is added to already exists.
     * Checks that the student tied to the class group already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted class group.
     */
    public ClassGroup toModelType(List<TaModule> taModuleList, List<Student> studentList) throws IllegalValueException {
        if (classGroupId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupId.class.getSimpleName()));
        }
        if (!ClassGroupId.isValidClassGroupId(classGroupId)) {
            throw new IllegalValueException(ClassGroupId.MESSAGE_CONSTRAINTS);
        }
        final ClassGroupId modelClassGroupId = new ClassGroupId(classGroupId);

        if (classGroupType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupType.class.getSimpleName()));
        }
        final ClassGroupType modelClassGroupType;
        switch (classGroupType) {
        case "Lab":
            modelClassGroupType = ClassGroupType.LAB;
            break;
        case "Recitation":
            modelClassGroupType = ClassGroupType.RECITATION;
            break;
        case "Sectional":
            modelClassGroupType = ClassGroupType.SECTIONAL;
            break;
        case "Tutorial":
            modelClassGroupType = ClassGroupType.TUTORIAL;
            break;
        default:
            throw new IllegalValueException("Invalid value for ClassGroupType");
        }

        if (module == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName()));
        }
        final TaModule modelModule = module.toModelType();
        if (!taModuleList.contains(modelModule)) {
            throw new IllegalValueException(NONEXISTENT_MODULE);
        }

        final List<Student> modelStudents = new ArrayList<>();
        for (JsonAdaptedStudent s : students) {
            Student sObj = s.toModelType();
            if (!studentList.contains(s)) {
                throw new IllegalValueException(NONEXISTENT_STUDENT);
            }
            modelStudents.add(sObj);
        }

        return new ClassGroup(modelClassGroupId, modelClassGroupType, modelModule, modelStudents);
    }
}
