package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.testutil.module.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        final EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS2103);
        assertTrue(DESC_CS2103.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2103.equals(DESC_CS2103));

        // null -> returns false
        assertFalse(DESC_CS2103.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2103.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2101.equals(DESC_CS2103));

        // different name -> returns false
        EditModuleDescriptor editedModule = new EditModuleDescriptorBuilder(DESC_CS2101)
                .withCode(VALID_MODULE_LECTURE).build();
        assertFalse(DESC_CS2101.equals(editedModule));
    }
}
