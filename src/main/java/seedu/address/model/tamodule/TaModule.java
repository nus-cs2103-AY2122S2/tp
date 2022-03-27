package seedu.address.model.tamodule;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Represents a Module in the TAssist.
 * It is named TaModule as it conflicts with the class java.lang.Module.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class TaModule implements Entity {

    // Identity fields
    private final ModuleName moduleName;
    private final ModuleCode moduleCode;
    private final AcademicYear academicYear;
    private final UniqueStudentList uniqueStudentList;

    /**
     * Every field must be present and not null.
     * Used to initialize a new module with no students.
     */
    public TaModule(ModuleName moduleName, ModuleCode moduleCode, AcademicYear academicYear) {
        this(moduleName, moduleCode, academicYear, new UniqueStudentList());
    }

    /**
     * Every field must be present and not null.
     * Used to initialize a module from storage file.
     */
    public TaModule(ModuleName moduleName, ModuleCode moduleCode, AcademicYear academicYear,
                    UniqueStudentList uniqueStudentList) {
        requireAllNonNull(moduleName, moduleCode);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.academicYear = academicYear;
        this.uniqueStudentList = uniqueStudentList;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
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

    /**
     * Returns true if both modules have the same moduleCode and academicYear.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(TaModule otherTaModule) {
        if (otherTaModule == this) {
            return true;
        }

        return otherTaModule != null
                && otherTaModule.getModuleCode().equals(getModuleCode())
                && otherTaModule.getAcademicYear().equals(getAcademicYear());
    }

    /**
     * Returns a String representation of the module code and academic year.
     * These are the minimum fields required to uniquely represent a module class.
     */
    public String toUniqueRepresentation() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append(",")
                .append(getAcademicYear());
        return builder.toString();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.TA_MODULE;
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TaModule)) {
            return false;
        }

        TaModule otherTaModule = (TaModule) other;
        return otherTaModule.getModuleName().equals(getModuleName())
                && otherTaModule.getAcademicYear().equals(getAcademicYear())
                && otherTaModule.getModuleCode().equals(getModuleCode())
                && otherTaModule.getStudents().equals(getStudents());
    }



    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, moduleCode, academicYear, uniqueStudentList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Module Name: ")
                .append(getModuleName())
                .append("; Academic Year: ")
                .append(getAcademicYear());
        return builder.toString();
    }

}
