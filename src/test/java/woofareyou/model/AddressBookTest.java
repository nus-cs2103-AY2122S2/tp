package woofareyou.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_ADDRESS_BOBA;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static woofareyou.testutil.Assert.assertThrows;

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
import woofareyou.testutil.Assert;
import woofareyou.testutil.TypicalPets;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPetList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWoofAreYou_replacesData() {
        AddressBook newData = TypicalPets.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePets_throwsDuplicatePetException() {
        // Two pets with the same identity fields
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Pet> newPets = Arrays.asList(TypicalPets.BOBA, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPets);

        Assert.assertThrows(DuplicatePetException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.hasPet(null));
    }

    @Test
    public void hasPet_petNotInWoofAreYou_returnsFalse() {
        assertFalse(addressBook.hasPet(TypicalPets.BOBA));
    }

    @Test
    public void hasPet_petInWoofAreYou_returnsTrue() {
        addressBook.addPet(TypicalPets.BOBA);
        assertTrue(addressBook.hasPet(TypicalPets.BOBA));
    }

    @Test
    public void hasPet_petWithSameIdentityFieldsInWoofAreYou_returnsTrue() {
        addressBook.addPet(TypicalPets.BOBA);
        Pet editedAlice = new PetBuilder(TypicalPets.BOBA).withAddress(VALID_ADDRESS_BOBA).withTags(VALID_TAG_FRIEND)
                .build();
        assertTrue(addressBook.hasPet(editedAlice));
    }

    @Test
    public void getPetList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> addressBook.getPetList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose pets list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Pet> pets = FXCollections.observableArrayList();

        AddressBookStub(Collection<Pet> pets) {
            this.pets.setAll(pets);
        }

        @Override
        public ObservableList<Pet> getPetList() {
            return pets;
        }
    }

}
