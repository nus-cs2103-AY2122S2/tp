package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Office;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Professor;
import seedu.address.model.person.Student;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_CODE = "CS2103";
    public static final String DEFAULT_NAME = "Software Engineering";
    public static final String DEFAULT_PROFESSOR = "Damith";

    private ModuleName moduleName;
    private ModuleCode moduleCode;
    private Professor professor;
    private List<Student> students;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_CODE);
        professor = new Professor(new Name(DEFAULT_PROFESSOR),
                new Phone("98765432"), new Email("test@nus.edu.sg"), new HashSet<>(), new Office("SOC"));
        students = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getModuleName();
        moduleCode = moduleToCopy.getModuleCode();
        professor = moduleToCopy.getProfessor();
        students = moduleToCopy.getStudents();
    }

    /**
     * Sets the {@code moduleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }

    /**
     * Sets the {@code moduleCode} of the {@code Module} that we are building.
     */
    public ModuleBuilder withModuleCode(String code) {
        this.moduleCode = new ModuleCode(code);
        return this;
    }

    /**
     * Sets the {@code professor} of the {@code Module} that we are building.
     */
    public ModuleBuilder withProfessor(String profName) {
        this.professor = new Professor(new Name(profName), null, null, null, null);
        return this;
    }

    public Module build() {
        return new Module(moduleName, moduleCode, professor, students);
    }

}
