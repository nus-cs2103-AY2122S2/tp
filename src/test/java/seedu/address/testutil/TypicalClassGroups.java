package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.classgroup.ClassGroup;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalClassGroups {
    public static final ClassGroup CS2101G09 = new ClassGroupBuilder().withClassGroupId("A01")
            .withClassGroupType("SECTIONAL").withModule("Effective Communication", "CS2101", "y2s2").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClassGroups() {} // prevents instantiation

    public static List<ClassGroup> getTypicalClassGroups() {
        return new ArrayList<>(Arrays.asList(CS2101G09));
    }
}
