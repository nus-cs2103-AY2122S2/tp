package woofareyou.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.UniquePetList;

/**
 * Wraps all data at the pet-book level
 * Duplicates are not allowed (by .isSamePet comparison)
 */
public class PetBook implements ReadOnlyPetBook {

    private final UniquePetList pets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        pets = new UniquePetList();
    }

    public PetBook() {}

    /**
     * Creates an PetBook using the Pets in the {@code toBeCopied}
     */
    public PetBook(ReadOnlyPetBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the pet list with {@code pets}.
     * {@code pets} must not contain duplicate pets.
     */
    public void setPets(List<Pet> pets) {
        this.pets.setPets(pets);
    }

    /**
     * Resets the existing data of this {@code PetBook} with {@code newData}.
     */
    public void resetData(ReadOnlyPetBook newData) {
        requireNonNull(newData);
        setPets(newData.getPetList());
    }

    //// pet-level operations

    /**
     * Returns true if a pet with the same identity as {@code pet} exists in PetBook.
     */
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return pets.contains(pet);
    }

    /**
     * Adds a pet to the pet book.
     * The pet must not already exist in the pet book.
     */
    public void addPet(Pet p) {
        pets.add(p);
    }

    /**
     * Sorts the list of pets in the pet book.
     * Currently, it will sort the list of pets according to the field provided.
     */
    public void sortPets(String field) {
        pets.sortPetList(field);
    }

    /**
     * Replaces the given pet {@code target} in the list with {@code editedPet}.
     * {@code target} must exist in WoofAreYou.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in WoofAreYou.
     */
    public void setPet(Pet target, Pet editedPet) {
        requireNonNull(editedPet);

        pets.setPet(target, editedPet);
    }


    /**
     * Removes {@code key} from this {@code PetBook}.
     * {@code key} must exist in the pet book.
     */
    public void removePet(Pet key) {
        pets.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return pets.asUnmodifiableObservableList().size() + " pets";
        // TODO: refine later
    }

    @Override
    public ObservableList<Pet> getPetList() {
        return pets.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PetBook // instanceof handles nulls
                && pets.equals(((PetBook) other).pets));
    }

    @Override
    public int hashCode() {
        return pets.hashCode();
    }

}
