package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_ORDER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_ORDER;

import org.junit.jupiter.api.Test;

public class EditOrderDescriptorTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DESC_AMY_ORDER.equals(DESC_AMY_ORDER));

        // null -> returns false
        assertFalse(DESC_AMY_ORDER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_ORDER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_ORDER.equals(DESC_BOB_ORDER));
    }
}
