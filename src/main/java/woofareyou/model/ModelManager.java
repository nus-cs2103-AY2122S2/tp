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

    private final VersionedPetBook versionedPetBook;
    private final PetBook petBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Pet> filteredPets;
    private Predicate<Pet> lastUsedPredicate;

    /**
     * Initializes a ModelManager with the given petBook and userPrefs.
     */
    public ModelManager(ReadOnlyPetBook petBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(petBook, userPrefs);
        logger.fine("Initializing with pet book: " + petBook + " and user prefs " + userPrefs);

        this.petBook = new PetBook(petBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.versionedPetBook = new VersionedPetBook(this.petBook);
        filteredPets = new FilteredList<>(this.petBook.getPetList());
        this.lastUsedPredicate = PREDICATE_SHOW_ALL_PETS;
    }

    /**
     * Initializes a ModelManager with the given petBook, userPrefs and predicate.
     */
    public ModelManager(ReadOnlyPetBook petBook, ReadOnlyUserPrefs userPrefs, Predicate<Pet> predicate) {
        requireAllNonNull(petBook, userPrefs);
        logger.fine("Initializing with pet book: " + petBook + " and user prefs " + userPrefs
                + " and predicate " + predicate);

        this.petBook = new PetBook(petBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.versionedPetBook = new VersionedPetBook(this.petBook);
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
    public void setPetBookFilePath(Path petBookFilePath) {
        requireNonNull(petBookFilePath);
        userPrefs.setPetBookFilePath(petBookFilePath);
    }

    //=========== PetBook ================================================================================

    @Override
    public void setPetBook(ReadOnlyPetBook petBook) {
        this.petBook.resetData(petBook);
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
        this.versionedPetBook.commit(this.getPetBook());
    }

    @Override
    public void addPet(Pet pet) {
        petBook.addPet(pet);
        this.versionedPetBook.commit(this.getPetBook());
        updateFilteredPetList();
    }

    @Override
    public void setPet(Pet target, Pet editedPet) {
        requireAllNonNull(target, editedPet);
        petBook.setPet(target, editedPet);
        this.versionedPetBook.commit(this.getPetBook());
    }

    /** Method that sorts the pet list via the sortPets() command in petBook. **/
    @Override
    public void sortPetList(String field) {
        requireNonNull(field);
        petBook.sortPets(field);
        this.versionedPetBook.commit(this.getPetBook());
    }


    //============= Undo Command accessors ================//
    @Override
    public ReadOnlyPetBook undo() throws Exception {
        return versionedPetBook.undo();
    }

    //=========== Filtered Pet List Accessors =============================================================

    @Override
    public Predicate<Pet> getLastUsedPredicate() {
        return lastUsedPredicate;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Pet} backed by the internal list of
     * {@code versionedPetBook}
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
