package woofareyou.model.pet;


import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB_WITH_SPACES;
import static woofareyou.logic.commands.CommandTestUtil.VALID_OWNER_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import woofareyou.testutil.Assert;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.TypicalPets;

public class PetTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Pet pet = new PetBuilder().build();
        Assert.assertThrows(UnsupportedOperationException.class, () -> pet.getTags().remove(0));
    }

    @Test
    public void isSamePet() {
        // same object -> returns true
        Assertions.assertTrue(TypicalPets.BOBA.isSamePet(TypicalPets.BOBA));

        // null -> returns false
        Assertions.assertFalse(TypicalPets.BOBA.isSamePet(null));

        // same name, all other attributes different -> returns true
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withPhone(VALID_PHONE_BOB)
                .withOwnerName(VALID_OWNER_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertTrue(TypicalPets.BOBA.isSamePet(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PetBuilder(TypicalPets.BOBA).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPets.BOBA.isSamePet(editedAlice));

        // name differs in case, all other attributes same -> returns true
        Pet editedBob = new PetBuilder(TypicalPets.BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        Assertions.assertTrue(TypicalPets.BOB.isSamePet(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PetBuilder(TypicalPets.BOB).withName(nameWithTrailingSpaces).build();
        Assertions.assertTrue(TypicalPets.BOB.isSamePet(editedBob));

        // name has duplicate spaces, all other attributes same -> returns true
        String nameWithDuplicateSpaces = VALID_NAME_BOB_WITH_SPACES;
        editedBob = new PetBuilder(TypicalPets.BOB).withName(nameWithDuplicateSpaces).build();
        Assertions.assertTrue(TypicalPets.BOB.isSamePet(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Pet aliceCopy = new PetBuilder(TypicalPets.BOBA).build();
        Assertions.assertTrue(TypicalPets.BOBA.equals(aliceCopy));

        // same object -> returns true
        Assertions.assertTrue(TypicalPets.BOBA.equals(TypicalPets.BOBA));

        // null -> returns false
        Assertions.assertFalse(TypicalPets.BOBA.equals(null));

        // different type -> returns false
        Assertions.assertFalse(TypicalPets.BOBA.equals(5));

        // different pet -> returns false
        Assertions.assertFalse(TypicalPets.BOBA.equals(TypicalPets.BOB));

        // different name -> returns false
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withName(VALID_NAME_BOB).build();
        Assertions.assertFalse(TypicalPets.BOBA.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PetBuilder(TypicalPets.BOBA).withPhone(VALID_PHONE_BOB).build();
        Assertions.assertFalse(TypicalPets.BOBA.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PetBuilder(TypicalPets.BOBA).withOwnerName(VALID_OWNER_NAME_BOB).build();
        Assertions.assertFalse(TypicalPets.BOBA.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PetBuilder(TypicalPets.BOBA).withAddress(VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(TypicalPets.BOBA.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PetBuilder(TypicalPets.BOBA).withTags(VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(TypicalPets.BOBA.equals(editedAlice));
    }
}
