package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_TUTORIAL;
import static seedu.address.testutil.typical.TypicalModules.CS2101;
import static seedu.address.testutil.typical.TypicalModules.CS2103;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.module.ModuleBuilder;

public class ModuleTest {
    @Test
    public void equals() {
        // same values -> returns true
        final Module cs2103Copy = new ModuleBuilder(CS2103).build();
        assertTrue(CS2103.equals(cs2103Copy));

        // same object -> returns true
        assertTrue(CS2103.equals(CS2103));

        // null -> returns false
        assertFalse(CS2103.equals(null));

        // different type -> returns false
        assertFalse(CS2103.equals("CS2103"));

        // different module -> returns false
        assertFalse(CS2103.equals(CS2101));

        // different name -> returns false
        Module editedCS2103 = new ModuleBuilder(CS2103).withCode(VALID_MODULE_TUTORIAL).build();
        assertFalse(CS2103.equals(editedCS2103));

        // name differs in case, all other attributes same -> returns false
        editedCS2103 = new ModuleBuilder(CS2103).withCode(VALID_MODULE_LECTURE.toLowerCase()).build();
        assertFalse(CS2103.equals(editedCS2103));

        // name has trailing spaces, all other attributes same -> returns false
        final String nameWithTrailingSpaces = VALID_MODULE_TUTORIAL + "  ";
        final Module editedCS2101 = new ModuleBuilder(CS2101).withCode(nameWithTrailingSpaces).build();
        assertFalse(CS2101.equals(editedCS2101));
    }
}
