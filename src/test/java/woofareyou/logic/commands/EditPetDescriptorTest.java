package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import woofareyou.testutil.EditPetDescriptorBuilder;

public class EditPetDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditPetDescriptor descriptorWithSameValues = new EditCommand.EditPetDescriptor(CommandTestUtil.DESC_AMY);
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditCommand.EditPetDescriptor editedAmy = new EditPetDescriptorBuilder(CommandTestUtil.DESC_AMY).withName(CommandTestUtil.VALID_NAME_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPetDescriptorBuilder(CommandTestUtil.DESC_AMY).withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPetDescriptorBuilder(CommandTestUtil.DESC_AMY).withOwnerName(CommandTestUtil.VALID_OWNER_NAME_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPetDescriptorBuilder(CommandTestUtil.DESC_AMY).withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPetDescriptorBuilder(CommandTestUtil.DESC_AMY).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
