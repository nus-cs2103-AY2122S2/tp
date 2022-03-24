package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB_WITH_SPACES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OWNER_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.BOB;
import static seedu.address.testutil.TypicalPets.BOBA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PetBuilder;

public class PetTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Pet pet = new PetBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> pet.getTags().remove(0));
    }

    @Test
    public void isSamePet() {
        // same object -> returns true
        assertTrue(BOBA.isSamePet(BOBA));

        // null -> returns false
        assertFalse(BOBA.isSamePet(null));

        // same name, all other attributes different -> returns true
        Pet editedAlice = new PetBuilder(BOBA).withPhone(VALID_PHONE_BOB).withOwnerName(VALID_OWNER_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(BOBA.isSamePet(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PetBuilder(BOBA).withName(VALID_NAME_BOB).build();
        assertFalse(BOBA.isSamePet(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Pet editedBob = new PetBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSamePet(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PetBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSamePet(editedBob));

        // name has duplicate spaces, all other attributes same -> returns true
        String nameWithDuplicateSpaces = VALID_NAME_BOB_WITH_SPACES;
        editedBob = new PetBuilder(BOB).withName(nameWithDuplicateSpaces).build();
        assertTrue(BOB.isSamePet(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Pet aliceCopy = new PetBuilder(BOBA).build();
        assertTrue(BOBA.equals(aliceCopy));

        // same object -> returns true
        assertTrue(BOBA.equals(BOBA));

        // null -> returns false
        assertFalse(BOBA.equals(null));

        // different type -> returns false
        assertFalse(BOBA.equals(5));

        // different pet -> returns false
        assertFalse(BOBA.equals(BOB));

        // different name -> returns false
        Pet editedAlice = new PetBuilder(BOBA).withName(VALID_NAME_BOB).build();
        assertFalse(BOBA.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PetBuilder(BOBA).withPhone(VALID_PHONE_BOB).build();
        assertFalse(BOBA.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PetBuilder(BOBA).withOwnerName(VALID_OWNER_NAME_BOB).build();
        assertFalse(BOBA.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PetBuilder(BOBA).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(BOBA.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PetBuilder(BOBA).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(BOBA.equals(editedAlice));
    }
}
