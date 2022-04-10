package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tamodule.TaModule;

/**
 * A utility class containing a list of {@code TaModule} objects to be used in tests.
 */
public class TypicalModules {
    public static final TaModule CS2101 = new ModuleBuilder().withModuleName("Effective Communication")
            .withModuleCode("CS2101").withAcademicYear("21S1")
            .withUniqueStudentList(TypicalStudents.BENSON).build();
    public static final TaModule CS2103T = new ModuleBuilder().withModuleName("Software Engineering")
            .withModuleCode("CS2103T").withAcademicYear("21S1").build();
    public static final TaModule CS2103T_WITH_STUDENT = new ModuleBuilder().withModuleName("Software Engineering")
            .withModuleCode("CS2103T").withAcademicYear("21S2")
            .withStudentList(TypicalStudents.getTypicalStudents()).build();
    public static final TaModule CS2030 = new ModuleBuilder().withModuleName("Programming Methodology II")
            .withModuleCode("CS2030").withAcademicYear("20S2")
            .withUniqueStudentList(TypicalStudents.ALICE, TypicalStudents.ELLE, TypicalStudents.FIONA).build();
    public static final TaModule CS2040 = new ModuleBuilder().withModuleName("Data Structures and Algorithms")
            .withModuleCode("CS2040").withAcademicYear("20S2")
            .withUniqueStudentList(TypicalStudents.ALICE).build();
    public static final TaModule CS2105 = new ModuleBuilder().withModuleName("Introduction to Computer Networks")
            .withModuleCode("CS2105").withAcademicYear("22S1")
            .withUniqueStudentList(TypicalStudents.CARL, TypicalStudents.DANIEL).build();

    private TypicalModules() {} // prevents instantiation

    public static List<TaModule> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2101, CS2103T, CS2030, CS2040, CS2105, CS2103T_WITH_STUDENT));
    }
}
