package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPets.BOBA;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.pet.Pet;
import seedu.address.model.pet.exceptions.DuplicatePetException;
import seedu.address.testutil.PetBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPetList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWoofAreYou_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePets_throwsDuplicatePetException() {
        // Two pets with the same identity fields
        Pet editedAlice = new PetBuilder(BOBA).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Pet> newPets = Arrays.asList(BOBA, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPets);

        assertThrows(DuplicatePetException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPet_nullPet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPet(null));
    }

    @Test
    public void hasPet_petNotInWoofAreYou_returnsFalse() {
        assertFalse(addressBook.hasPet(BOBA));
    }

    @Test
    public void hasPet_petInWoofAreYou_returnsTrue() {
        addressBook.addPet(BOBA);
        assertTrue(addressBook.hasPet(BOBA));
    }

    @Test
    public void hasPet_petWithSameIdentityFieldsInWoofAreYoureturnsTrue() {
        addressBook.addPet(BOBA);
        Pet editedAlice = new PetBuilder(BOBA).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPet(editedAlice));
    }

    @Test
    public void getPetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPetList().remove(0));
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
