package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.classgroup.ClassGroup;

/**
 * A utility class containing a list of {@code ClassGroup} objects to be used in tests.
 */
public class TypicalClassGroups {
    public static final int TOTAL_CLASS_GROUPS = 6;
    public static final ClassGroup CS2101G09 = getClassGroup(0);
    public static final ClassGroup CS2101G10 = getClassGroup(1);
    public static final ClassGroup CS2040B10A = getClassGroup(2);
    public static final ClassGroup CS2105T05 = getClassGroup(3);
    public static final ClassGroup CS2030R08 = getClassGroup(4);
    public static final ClassGroup CS2103TT13 = getClassGroup(5);

    private TypicalClassGroups() {} // prevents instantiation

    public static List<ClassGroup> getTypicalClassGroups() {
        List<ClassGroup> cgList = new ArrayList<>();
        for (int i = 0; i < TOTAL_CLASS_GROUPS; i++) {
            cgList.add(getClassGroup(i));
        }
        return cgList;
    }

    public static ClassGroup getClassGroup(int i) {
        switch (i) {
        case 0:
            return new ClassGroupBuilder().withClassGroupId("G09")
                .withClassGroupType("SECTIONAL").withModule(TypicalModules.getModule(CS2101))
                .withUniqueStudentList(TypicalStudents.BENSON)
                .withLessons(TypicalLessons.LESSON2).build();
        case 1:
            return new ClassGroupBuilder().withClassGroupId("G10")
                .withClassGroupType("SECTIONAL").withModule(TypicalModules.getModule(CS2101)).build();
        case 2:
            return new ClassGroupBuilder().withClassGroupId("B10A")
                .withClassGroupType("LAB").withModule(TypicalModules.getModule(CS2040))
                .withUniqueStudentList(TypicalStudents.ALICE)
                .withLessons(TypicalLessons.LESSON1).build();
        case 3:
            return new ClassGroupBuilder().withClassGroupId("T05")
                .withClassGroupType("TUTORIAL").withModule(TypicalModules.getModule(CS2101))
                .withUniqueStudentList(TypicalStudents.CARL, TypicalStudents.DANIEL)
                .withLessons(TypicalLessons.LESSON12).build();
        case 4:
            return new ClassGroupBuilder().withClassGroupId("R08")
                .withClassGroupType("RECITATION").withModule(TypicalModules.getModule(CS2030))
                .withUniqueStudentList(TypicalStudents.ALICE, TypicalStudents.ELLE, TypicalStudents.FIONA)
                .withLessons(TypicalLessons.LESSON1, TypicalLessons.LESSON13).build();
        case 5:
            return new ClassGroupBuilder().withClassGroupId("T13")
                .withClassGroupType("TUTORIAL").withModule(TypicalModules.getModule(CS2103T_WITH_STUDENT))
                .withUniqueStudentList(TypicalStudents.getTypicalStudents()).build();
        default:
            return new ClassGroupBuilder().build();
        }
    }
}
