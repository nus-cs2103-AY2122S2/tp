package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;


/**
 * Represents a Module in the UniBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Module {

    // Identity fields
    private final ModuleName moduleName;
    private final ModuleCode moduleCode;


    // Data fields
    private final Professor professor;
    private final List<Student> students;

    /**
     * Constructor for a Module, assuming no students initially present.
     * @param moduleName
     * @param moduleCode
     * @param professor
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode, Professor professor) {
        requireAllNonNull(moduleName, moduleCode, professor);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professor = professor;
        this.students = new ArrayList<>();
    }

    /**
     * Constructor for a Module, using a pre-existing list of students.
     * @param moduleName
     * @param moduleCode
     * @param professor
     * @param students
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode, Professor professor, List<Student> students) {
        requireAllNonNull(moduleName, moduleCode, professor, students);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professor = professor;
        this.students = students;
    }

    public ModuleName getModuleName() {
        return this.moduleName;
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    /**
     * Returns true if both modules have the same name and code.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getModuleName().equals(getModuleName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;

        return otherModule.getModuleName().equals(getModuleName())
                && otherModule.getModuleCode().equals(getModuleCode())
                && otherModule.getProfessor().equals(getProfessor())
                && otherModule.getStudents().equals(getStudents());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, moduleCode, professor, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Name: ")
                .append(getModuleName())
                .append("; Professor:")
                .append(getProfessor());
        return builder.toString();
    }

}
