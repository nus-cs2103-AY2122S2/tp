package woofareyou.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import woofareyou.commons.core.LogsCenter;
import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.ReadOnlyUserPrefs;
import woofareyou.model.UserPrefs;

/**
 * Manages storage of PetBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PetBookStorage petBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PetBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PetBookStorage petBookStorage, UserPrefsStorage userPrefsStorage) {
        this.petBookStorage = petBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PetBook methods ==============================

    @Override
    public Path getPetBookFilePath() {
        return petBookStorage.getPetBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPetBook> readPetBook() throws DataConversionException, IOException {
        return readPetBook(petBookStorage.getPetBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPetBook> readPetBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return petBookStorage.readPetBook(filePath);
    }

    @Override
    public void savePetBook(ReadOnlyPetBook petBook) throws IOException {
        savePetBook(petBook, petBookStorage.getPetBookFilePath());
    }

    @Override
    public void savePetBook(ReadOnlyPetBook petBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        petBookStorage.savePetBook(petBook, filePath);
    }

}
