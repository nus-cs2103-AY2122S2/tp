package woofareyou.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOBA;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.testutil.Assert.assertThrows;
import static woofareyou.testutil.TypicalPets.BOBA;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.exceptions.DuplicatePetException;
import woofareyou.testutil.PetBuilder;

public class PetBookTest {

    private final PetBook petBook = new PetBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), petBook.getPetList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> petBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWoofAreYou_replacesData() {
        PetBook newData = getTypicalPetBook();
        petBook.resetData(newData);
        assertEquals(newData, petBook);
    }

    @Test
    public void resetData_withDuplicatePets_throwsDuplicatePetException() {
        // Two pets with the same identity fields
        Pet editedAlice = new PetBuilder(BOBA).withAddress(VALID_ADDRESS_BOBA).withTags(VALID_TAG_FRIEND)
            .build();
        List<Pet> newPets = Arrays.asList(BOBA, editedAlice);
        PetBookStub newData = new PetBookStub(newPets);

        assertThrows(DuplicatePetException.class, () -> petBook.resetData(newData));
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> petBook.hasPet(null));
    }

    @Test
    public void hasPet_petNotInWoofAreYou_returnsFalse() {
        assertFalse(petBook.hasPet(BOBA));
    }

    @Test
    public void hasPet_petInWoofAreYou_returnsTrue() {
        petBook.addPet(BOBA);
        assertTrue(petBook.hasPet(BOBA));
    }

    @Test
    public void hasPet_petWithSameIdentityFieldsInWoofAreYou_returnsTrue() {
        petBook.addPet(BOBA);
        Pet editedAlice = new PetBuilder(BOBA).withAddress(VALID_ADDRESS_BOBA).withTags(VALID_TAG_FRIEND)
            .build();
        assertTrue(petBook.hasPet(editedAlice));
    }

    @Test
    public void getPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> petBook.getPetList().remove(0));
    }

    /**
     * A stub ReadOnlyPetBook whose pets list can violate interface constraints.
     */
    private static class PetBookStub implements ReadOnlyPetBook {
        private final ObservableList<Pet> pets = FXCollections.observableArrayList();

        PetBookStub(Collection<Pet> pets) {
            this.pets.setAll(pets);
        }

        @Override
        public ObservableList<Pet> getPetList() {
            return pets;
        }
    }

}
