package seedu.address.model.classgroup;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Represents a ClassGroup in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClassGroup implements Entity {
    // Identity fields
    private final ClassGroupId classGroupId;
    private final ClassGroupType classGroupType;
    private final TaModule taModule;
    private final UniqueStudentList uniqueStudentList;

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     * Used to initialize a new class group with no students.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param taModule A valid module.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule) {
        this(classGroupId, classGroupType, taModule, new UniqueStudentList());
    }

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     * Used to initialize a class group from storage file.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param taModule A valid module.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule,
                      UniqueStudentList uniqueStudentList) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.taModule = taModule;
        this.uniqueStudentList = uniqueStudentList;
    }



    public ClassGroupId getClassGroupId() {
        return classGroupId;
    }

    public ClassGroupType getClassGroupType() {
        return classGroupType;
    }

    public TaModule getModule() {
        return taModule;
    }


    public void addStudent(Student s) {
        uniqueStudentList.add(s);
    }

    public boolean hasStudent(Student s) {
        return uniqueStudentList.contains(s);
    }

    public void removeStudent(Student s) {
        uniqueStudentList.remove(s);
    }

    public ObservableList<Student> getStudents() {
        return uniqueStudentList.asUnmodifiableObservableList();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.CLASS_GROUP;
    }

    /**
     * Returns true if both class groups have the same identity and fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClassGroup)) {
            return false;
        }

        ClassGroup otherClassGroup = (ClassGroup) other;
        return otherClassGroup.getClassGroupId().equals(getClassGroupId())
                && otherClassGroup.getClassGroupType().equals(getClassGroupType())
                && otherClassGroup.getModule().equals(getModule())
                && otherClassGroup.getStudents().equals(getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(classGroupId, classGroupType, taModule, uniqueStudentList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassGroupId())
                .append("; Type: ")
                .append(getClassGroupType())
                .append("; Module Code: ")
                .append(getModule().getModuleCode())
                .append("; Academic Year: ")
                .append(getModule().getAcademicYear());
        return builder.toString();
    }
}
