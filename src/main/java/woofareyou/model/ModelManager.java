package woofareyou.model;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import woofareyou.commons.core.GuiSettings;
import woofareyou.commons.core.LogsCenter;
import woofareyou.model.pet.Pet;

/**
 * Represents the in-memory model of the WoofAreYou data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedPetBook versionedAddressBook;
    private final PetBook petBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Pet> filteredPets;
    private Predicate<Pet> lastUsedPredicate;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyPetBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.petBook = new PetBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.versionedAddressBook = new VersionedPetBook(this.petBook);
        filteredPets = new FilteredList<>(this.petBook.getPetList());
        this.lastUsedPredicate = PREDICATE_SHOW_ALL_PETS;
    }

    /**
     * Initializes a ModelManager with the given addressBook, userPrefs and predicate.
     */
    public ModelManager(ReadOnlyPetBook addressBook, ReadOnlyUserPrefs userPrefs, Predicate<Pet> predicate) {
        requireAllNonNull(addressBook, userPrefs);
        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
                + " and predicate " + predicate);

        this.petBook = new PetBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.versionedAddressBook = new VersionedPetBook(this.petBook);
        filteredPets = new FilteredList<>(this.petBook.getPetList());
        this.lastUsedPredicate = predicate;
        updateFilteredPetList(lastUsedPredicate);
    }

    public ModelManager() {
        this(new PetBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPetBookFilePath() {
        return userPrefs.getPetBookFilePath();
    }

    @Override
    public void setPetBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPetBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setPetBook(ReadOnlyPetBook addressBook) {
        this.petBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyPetBook getPetBook() {
        return petBook;
    }

    @Override
    public boolean hasPet(Pet pet) {
        requireNonNull(pet);
        return petBook.hasPet(pet);
    }

    @Override
    public void deletePet(Pet target) {
        petBook.removePet(target);
        this.versionedAddressBook.commit(this.getPetBook());
    }

    @Override
    public void addPet(Pet pet) {
        petBook.addPet(pet);
        this.versionedAddressBook.commit(this.getPetBook());
        updateFilteredPetList();
    }

    @Override
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);
        petBook.setPet(target, editedPet);
        this.versionedAddressBook.commit(this.getPetBook());
    }

    /** Method that sorts the pet list via the sortPets() command in addressBook. **/
    @Override
    public void sortPetList(String field) {
        requireNonNull(field);
        petBook.sortPets(field);
        this.versionedAddressBook.commit(this.getPetBook());
    }


    //============= Undo Command accessors ================//
    @Override
    public ReadOnlyPetBook undo() throws Exception {
        return versionedAddressBook.undo();
    }

    //=========== Filtered Pet List Accessors =============================================================

    @Override
    public Predicate<Pet> getLastUsedPredicate() {
        return lastUsedPredicate;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Pet} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Pet> getFilteredPetList() {
        return filteredPets;
    }

    @Override
    public void updateFilteredPetList(Predicate<Pet> predicate) {
        requireNonNull(predicate);
        this.lastUsedPredicate = predicate;
        filteredPets.setPredicate(lastUsedPredicate);
    }

    @Override
    public void updateFilteredPetList() {
        filteredPets.setPredicate(lastUsedPredicate);
    }

    @Override
    public void updateFilteredPetListToFullPetList() {
        this.lastUsedPredicate = PREDICATE_SHOW_ALL_PETS;
        filteredPets.setPredicate(lastUsedPredicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return petBook.equals(other.petBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPets.equals(other.filteredPets);
    }

}
