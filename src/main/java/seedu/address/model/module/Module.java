package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;
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
    private final List<Person> professors;
    private final List<Person> students;

    /**
     * Constructor for a Module, assuming no students and no professor initially.
     * can add if necessary.
     * @param moduleName
     * @param moduleCode
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode) {
        requireAllNonNull(moduleName, moduleCode);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professors = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    /**
     * Constructor for a Module, using a pre-existing list of students and professors.
     * @param moduleName
     * @param moduleCode
     * @param professors
     * @param students
     */
    public Module(ModuleName moduleName, ModuleCode moduleCode, List<Person> professors, List<Person> students) {
        requireAllNonNull(moduleName, moduleCode, professors, students);
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.professors = professors;
        this.students = students;
    }

    public ModuleName getModuleName() {
        return this.moduleName;
    }

    public ModuleCode getModuleCode() {
        return this.moduleCode;
    }

    public List<Person> getProfessors() {
        return this.professors;
    }

    public List<Person> getStudents() {
        return this.students;
    }

    public void addStudent(Student s) {
        this.students.add(s);
    }

    public void addProfessor(Professor p) {
        this.professors.add(p);
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
                && otherModule.getProfessors().equals(getProfessors())
                && otherModule.getStudents().equals(getStudents());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleName, moduleCode, professors, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getModuleCode())
                .append("; Name: ")
                .append(getModuleName())
                .append("; Professors: ")
                .append(getProfessors());
        return builder.toString();
    }

}
