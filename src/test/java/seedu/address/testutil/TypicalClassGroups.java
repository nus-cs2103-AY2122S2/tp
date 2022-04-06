package seedu.address.testutil;

import static seedu.address.testutil.TypicalLessons.LESSON1;
import static seedu.address.testutil.TypicalLessons.LESSON12;
import static seedu.address.testutil.TypicalLessons.LESSON13;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.classgroup.ClassGroup;

/**
 * A utility class containing a list of {@code ClassGroup} objects to be used in tests.
 */
public class TypicalClassGroups {
    public static final ClassGroup CS2101G09 = new ClassGroupBuilder().withClassGroupId("G09")
            .withClassGroupType("SECTIONAL").withModule(TypicalModules.CS2101).build();
    public static final ClassGroup CS2101G10 = new ClassGroupBuilder().withClassGroupId("G10")
            .withClassGroupType("SECTIONAL").withModule(TypicalModules.CS2101).build();
    public static final ClassGroup CS2040B10A = new ClassGroupBuilder().withClassGroupId("B10A")
            .withClassGroupType("LAB").withModule(TypicalModules.CS2040)
            .withUniqueStudentList(ALICE).withLessons(LESSON1).build();
    public static final ClassGroup CS2105T05 = new ClassGroupBuilder().withClassGroupId("T05")
            .withClassGroupType("TUTORIAL").withModule(TypicalModules.CS2101)
            .withUniqueStudentList(CARL, DANIEL).withLessons(LESSON12).build();
    public static final ClassGroup CS2030R08 = new ClassGroupBuilder().withClassGroupId("R08")
            .withClassGroupType("RECITATION").withModule(TypicalModules.CS2030)
            .withUniqueStudentList(ALICE, ELLE, FIONA).withLessons(LESSON1, LESSON13).build();

    private TypicalClassGroups() {} // prevents instantiation

    public static List<ClassGroup> getTypicalClassGroups() {
        return new ArrayList<>(Arrays.asList(CS2101G09, CS2101G10, CS2040B10A, CS2105T05, CS2030R08));
    }
}
