package woofareyou.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import woofareyou.commons.core.GuiSettings;
import woofareyou.model.pet.Pet;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Pet> PREDICATE_SHOW_ALL_PETS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' pet book file path.
     */
    Path getPetBookFilePath();

    /**
     * Sets the user prefs' pet book file path.
     */
    void setPetBookFilePath(Path petBookFilePath);

    /**
     * Replaces pet book data with the data in {@code petBook}.
     */
    void setPetBook(ReadOnlyPetBook petBook);

    /** Returns the PetBook */
    ReadOnlyPetBook getPetBook();

    /**
     * Returns true if a pet with the same identity as {@code pet} exists in the pet book.
     */
    boolean hasPet(Pet pet);

    /**
     * Deletes the given pet.
     * The pet must exist in the pet book.
     */
    void deletePet(Pet target);

    /**
     * Adds the given pet.
     * {@code pet} must not already exist in the pet book.
     */
    void addPet(Pet pet);

    /**
     * Replaces the given pet {@code target} with {@code editedPet}.
     * {@code target} must exist in the pet book.
     * The pet identity of {@code editedPet} must not be the same as another existing pet in the pet book.
     */
    void setPet(Pet target, Pet editedPet);

    /** Returns the last used predicate to filter pet list by the model. */
    Predicate<Pet> getLastUsedPredicate();

    /** Returns an unmodifiable view of the filtered pet list */
    ObservableList<Pet> getFilteredPetList();

    /**
     * Updates the filter of the filtered pet list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPetList(Predicate<Pet> predicate);

    /**
     * Updates the filter of the filtered pet list to filter by the last given {@code predicate}.
     * If no last used predicate is found, uses Predicate to display full Pet list.
     */
    void updateFilteredPetList();

    /**
     * Updates filtered pet list to display the entire pet list.
     */
    void updateFilteredPetListToFullPetList();

    /**
     * Sorts the pet list based on the field provided.
     */
    void sortPetList(String field);

    /**
     * Restores the previous pet book state from its history.
     */
    ReadOnlyPetBook undo() throws Exception;

}
