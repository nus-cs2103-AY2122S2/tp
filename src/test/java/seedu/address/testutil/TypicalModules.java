package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.tamodule.TaModule;

/**
 * A utility class containing a list of {@code TaModule} objects to be used in tests.
 */
public class TypicalModules {
    public static final int TOTAL_MODULES = 6;
    public static final TaModule CS2101 = getModule(0);
    public static final TaModule CS2103T = getModule(1);
    public static final TaModule CS2030 = getModule(2);
    public static final TaModule CS2040 = getModule(3);
    public static final TaModule CS2105 = getModule(4);
    public static final TaModule CS2103T_WITH_STUDENT = getModule(5);

    private TypicalModules() {} // prevents instantiation

    public static List<TaModule> getTypicalModules() {
        List<TaModule> moduleList = new ArrayList<>();
        for (int i = 0; i < TOTAL_MODULES; i++) {
            moduleList.add(getModule(i));
        }
        return moduleList;
    }

    public static TaModule getModule(int i) {
        switch (i) {
        case 0:
            return new ModuleBuilder().withModuleName("Effective Communication")
                    .withModuleCode("CS2101").withAcademicYear("21S1")
                    .withUniqueStudentList(TypicalStudents.BENSON).build();
        case 1:
            return new ModuleBuilder().withModuleName("Software Engineering")
                    .withModuleCode("CS2103T").withAcademicYear("21S1").build();
        case 2:
            return new ModuleBuilder().withModuleName("Programming Methodology II")
                    .withModuleCode("CS2030").withAcademicYear("20S2")
                    .withUniqueStudentList(TypicalStudents.ALICE, TypicalStudents.ELLE, TypicalStudents.FIONA).build();
        case 3:
            return new ModuleBuilder().withModuleName("Data Structures and Algorithms")
                    .withModuleCode("CS2040").withAcademicYear("20S2")
                    .withUniqueStudentList(TypicalStudents.ALICE).build();
        case 4:
            return new ModuleBuilder().withModuleName("Introduction to Computer Networks")
                    .withModuleCode("CS2105").withAcademicYear("22S1")
                    .withUniqueStudentList(TypicalStudents.CARL, TypicalStudents.DANIEL).build();
        case 5:
            return new ModuleBuilder().withModuleName("Software Engineering")
                    .withModuleCode("CS2103T").withAcademicYear("21S2")
                    .withStudentList(TypicalStudents.getTypicalStudents()).build();
        default:
            return new ModuleBuilder().build();
        }
    }
}
