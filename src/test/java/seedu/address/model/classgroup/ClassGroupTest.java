package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_ID_CS2106_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_TYPE_CS2106_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_CODE_CS2106_LAB;
import static seedu.address.testutil.TypicalClassGroups.CS2103T_TUT;
import static seedu.address.testutil.TypicalClassGroups.CS2106_LAB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClassGroupBuilder;

public class ClassGroupTest {
    @Test
    public void equals() {
        // same values -> returns true
        ClassGroup cs2103tCopy = new ClassGroupBuilder(CS2103T_TUT).build();
        assertTrue(CS2103T_TUT.equals(cs2103tCopy));

        // same object -> returns true
        assertTrue(CS2103T_TUT.equals(CS2103T_TUT));

        // null -> returns false
        assertFalse(CS2103T_TUT.equals(null));

        // different type -> returns false
        assertFalse(CS2103T_TUT.equals(5));

        // different class group -> returns false
        assertFalse(CS2103T_TUT.equals(CS2106_LAB));

        // different class group ID -> returns false
        ClassGroup editedCs2103t = new ClassGroupBuilder(CS2103T_TUT).withClassGroupId(VALID_CG_ID_CS2106_LAB).build();
        assertFalse(CS2103T_TUT.equals(editedCs2103t));

        // different class group type -> returns false
        editedCs2103t = new ClassGroupBuilder(CS2103T_TUT).withClassGroupType(VALID_CG_TYPE_CS2106_LAB).build();
        assertFalse(CS2103T_TUT.equals(editedCs2103t));

        // different module code -> returns false
        editedCs2103t = new ClassGroupBuilder(CS2103T_TUT).withModuleCode(VALID_MOD_CODE_CS2106_LAB).build();
        assertFalse(CS2103T_TUT.equals(editedCs2103t));
    }
}
