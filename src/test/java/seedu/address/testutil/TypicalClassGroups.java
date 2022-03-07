package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupType;

/**
 * A utility class containing a list of {@code ClassGroup} objects to be used in tests.
 */
public class TypicalClassGroups {
    public static final ClassGroup CS2103T_TUT = new ClassGroupBuilder().withClassGroupId("T13")
            .withClassGroupType(ClassGroupType.TUTORIAL)
            .withModuleCode("CS2103T").build();
    public static final ClassGroup CS2106_LAB = new ClassGroupBuilder().withClassGroupId("B01")
            .withClassGroupType(ClassGroupType.LAB)
            .withModuleCode("CS2106").build();

    public static List<ClassGroup> getTypicalClassGroups() {
        return new ArrayList<>(Arrays.asList(CS2103T_TUT, CS2106_LAB));
    }
}
