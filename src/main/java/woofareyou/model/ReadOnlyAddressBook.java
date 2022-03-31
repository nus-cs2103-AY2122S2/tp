package woofareyou.model;

import javafx.collections.ObservableList;
import woofareyou.model.pet.Pet;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the pets list.
     * This list will not contain any duplicate pets.
     */
    ObservableList<Pet> getPetList();

}
