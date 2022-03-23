package unibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unibook.logic.commands.CommandTestUtil.DESC_ALEX;
import static unibook.logic.commands.CommandTestUtil.DESC_BERNICE;
import static unibook.logic.commands.CommandTestUtil.VALID_EMAIL_STUDENT_BERNICE;
import static unibook.logic.commands.CommandTestUtil.VALID_NAME_STUDENT_BERNICE;
import static unibook.logic.commands.CommandTestUtil.VALID_PHONE_STUDENT_BERNICE;
import static unibook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static unibook.logic.commands.CommandTestUtil.VALID_TAG_ROOMMATE;

import org.junit.jupiter.api.Test;

import unibook.logic.commands.EditCommand.EditPersonDescriptor;
import unibook.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_ALEX);
        assertTrue(DESC_ALEX.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALEX.equals(DESC_ALEX));

        // null -> returns false
        assertFalse(DESC_ALEX.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALEX.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALEX.equals(DESC_BERNICE));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withName(VALID_NAME_STUDENT_BERNICE).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withPhone(VALID_PHONE_STUDENT_BERNICE).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withEmail(VALID_EMAIL_STUDENT_BERNICE).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withTags(VALID_TAG_FRIEND, VALID_TAG_ROOMMATE).build();
        assertFalse(DESC_ALEX.equals(editedAmy));
    }
}
