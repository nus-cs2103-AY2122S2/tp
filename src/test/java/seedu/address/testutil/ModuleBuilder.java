package seedu.address.testutil;

import java.util.List;

import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * A utility class to help with building Module objects.
 */
public class ModuleBuilder {

    public static final String DEFAULT_MODULE_NAME = "Software Engineering";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final String DEFAULT_ACADEMIC_YEAR = "21S1";

    private ModuleName moduleName;
    private ModuleCode moduleCode;
    private AcademicYear academicYear;
    private UniqueStudentList studentList = new UniqueStudentList();

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_MODULE_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        academicYear = new AcademicYear(DEFAULT_ACADEMIC_YEAR);
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code moduleToCopy}.
     */
    public ModuleBuilder(TaModule moduleToCopy) {
        moduleName = moduleToCopy.getModuleName();
        moduleCode = moduleToCopy.getModuleCode();
        academicYear = moduleToCopy.getAcademicYear();
        studentList.setStudents(moduleToCopy.getStudents());
    }

    /**
     * Sets the {@code ModuleName} of the {@code TaModule} that we are building.
     */
    public ModuleBuilder withModuleName(String moduleName) {
        this.moduleName = new ModuleName(moduleName);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code TaModule} that we are building.
     */
    public ModuleBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code AcademicYear} of the {@code Module} that we are building.
     */
    public ModuleBuilder withAcademicYear(String academicYear) {
        this.academicYear = new AcademicYear(academicYear);
        return this;
    }

    /**
     * Sets the {@code studentList} of the {@code Module} that we are building with a list of students.
     */
    public ModuleBuilder withStudentList(List<Student> studentList) {
        this.studentList.setStudents(studentList);
        return this;
    }

    /**
     * Sets the {@code studentList} of the {@code Module} that we are building with variable number of students.
     */
    public ModuleBuilder withUniqueStudentList(Student... students) {
        UniqueStudentList newStudentList = new UniqueStudentList();
        for (Student s : students) {
            newStudentList.add(s);
        }
        studentList.setStudents(newStudentList);
        return this;
    }


    public TaModule build() {
        return new TaModule(moduleName, moduleCode, academicYear, studentList);
    }

}
