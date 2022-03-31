package woofareyou.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOBA;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import woofareyou.model.pet.exceptions.DuplicatePetException;
import woofareyou.model.pet.exceptions.PetNotFoundException;
import woofareyou.testutil.Assert;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.TypicalPets;

public class UniquePetListTest {

    private final UniquePetList uniquePetList = new UniquePetList();

    @Test
    public void contains_nullPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.contains(null));
    }

    @Test
    public void contains_petNotInList_returnsFalse() {
        assertFalse(uniquePetList.contains(TypicalPets.BOBA));
    }

    @Test
    public void contains_petInList_returnsTrue() {
        uniquePetList.add(TypicalPets.BOBA);
        assertTrue(uniquePetList.contains(TypicalPets.BOBA));
    }

    @Test
    public void contains_petWithSameIdentityFieldsInList_returnsTrue() {
        uniquePetList.add(TypicalPets.BOBA);
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withAddress(VALID_ADDRESS_BOBA).withTags(VALID_TAG_FRIEND)
                .build();
        assertTrue(uniquePetList.contains(editedAlice));
    }

    @Test
    public void add_nullPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.add(null));
    }

    @Test
    public void add_duplicatePet_throwsDuplicatePetException() {
        uniquePetList.add(TypicalPets.BOBA);
        Assert.assertThrows(DuplicatePetException.class, () -> uniquePetList.add(TypicalPets.BOBA));
    }

    @Test
    public void setPet_nullTargetPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.setPet(null, TypicalPets.BOBA));
    }

    @Test
    public void setPet_nullEditedPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.setPet(TypicalPets.BOBA, null));
    }

    @Test
    public void setPet_targetPetNotInList_throwsPetNotFoundException() {
        Assert.assertThrows(PetNotFoundException.class, () -> uniquePetList.setPet(TypicalPets.BOBA, TypicalPets.BOBA));
    }

    @Test
    public void setPet_editedPetIsSamePet_success() {
        uniquePetList.add(TypicalPets.BOBA);
        uniquePetList.setPet(TypicalPets.BOBA, TypicalPets.BOBA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.BOBA);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasSameIdentity_success() {
        uniquePetList.add(TypicalPets.BOBA);
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePetList.setPet(TypicalPets.BOBA, editedAlice);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(editedAlice);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasDifferentIdentity_success() {
        uniquePetList.add(TypicalPets.BOBA);
        uniquePetList.setPet(TypicalPets.BOBA, TypicalPets.BOB);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.BOB);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_editedPetHasNonUniqueIdentity_throwsDuplicatePetException() {
        uniquePetList.add(TypicalPets.BOBA);
        uniquePetList.add(TypicalPets.BOB);
        Assert.assertThrows(DuplicatePetException.class, () -> uniquePetList.setPet(TypicalPets.BOBA, TypicalPets.BOB));
    }

    @Test
    public void remove_nullPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.remove(null));
    }

    @Test
    public void remove_petDoesNotExist_throwsPetNotFoundException() {
        Assert.assertThrows(PetNotFoundException.class, () -> uniquePetList.remove(TypicalPets.BOBA));
    }

    @Test
    public void remove_existingPet_removesPet() {
        uniquePetList.add(TypicalPets.BOBA);
        uniquePetList.remove(TypicalPets.BOBA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPet_nullUniquePetList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.setPets((UniquePetList) null));
    }

    @Test
    public void setPets_uniquePetList_replacesOwnListWithProvidedUniquePetList() {
        uniquePetList.add(TypicalPets.BOBA);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.BOB);
        uniquePetList.setPets(expectedUniquePetList);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_nullList_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> uniquePetList.setPets((List<Pet>) null));
    }

    @Test
    public void setPets_list_replacesOwnListWithProvidedList() {
        uniquePetList.add(TypicalPets.BOBA);
        List<Pet> petList = Collections.singletonList(TypicalPets.BOB);
        uniquePetList.setPets(petList);
        UniquePetList expectedUniquePetList = new UniquePetList();
        expectedUniquePetList.add(TypicalPets.BOB);
        assertEquals(expectedUniquePetList, uniquePetList);
    }

    @Test
    public void setPets_listWithDuplicatePets_throwsDuplicatePetException() {
        List<Pet> listWithDuplicatePets = Arrays.asList(TypicalPets.BOBA, TypicalPets.BOBA);
        Assert.assertThrows(DuplicatePetException.class, () -> uniquePetList.setPets(listWithDuplicatePets));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
            -> uniquePetList.asUnmodifiableObservableList().remove(0));
    }
}
