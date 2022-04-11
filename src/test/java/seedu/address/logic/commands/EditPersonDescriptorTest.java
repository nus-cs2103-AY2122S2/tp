package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ALEX;
import static seedu.address.logic.commands.CommandTestUtil.BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_1_AMOUNT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditPersonCommand.EditPersonDescriptor descriptorWithSameValues =
                new EditPersonCommand.EditPersonDescriptor(ALEX);
        assertTrue(ALEX.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ALEX.equals(ALEX));

        // null -> returns false
        assertFalse(ALEX.equals(null));

        // different types -> returns false
        assertFalse(ALEX.equals(5));

        // different person -> returns false
        assertFalse(ALEX.equals(BOB));

        // different name -> returns false
        EditPersonCommand.EditPersonDescriptor editedBob = new EditPersonDescriptorBuilder(BOB)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        assertFalse(ALEX.equals(BOB));


        editedBob = new EditPersonDescriptorBuilder(BOB)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        assertFalse(BOB.equals(editedBob));

        editedBob = new EditPersonDescriptorBuilder(BOB)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        assertFalse(BOB.equals(editedBob));

        editedBob = new EditPersonDescriptorBuilder(BOB)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        assertFalse(BOB.equals(editedBob));


        editedBob = new EditPersonDescriptorBuilder(BOB)
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        assertFalse(BOB.equals(editedBob));
    }
}
