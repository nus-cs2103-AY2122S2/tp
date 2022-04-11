package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final MedBookStorage medBookStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MedBookStorage medBookStorage, UserPrefsStorage userPrefsStorage) {
        this.medBookStorage = medBookStorage;
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

    // ================ AddressBook methods ==============================

    @Override
    public Path getMedBookFilePath() {
        return medBookStorage.getMedBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMedBook> readMedBook() throws DataConversionException, IOException {
        return readMedBook(medBookStorage.getMedBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMedBook> readMedBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return medBookStorage.readMedBook(filePath);
    }

    @Override
    public void saveMedBook(ReadOnlyMedBook addressBook) throws IOException {
        saveMedBook(addressBook, medBookStorage.getMedBookFilePath());
    }

    @Override
    public void saveMedBook(ReadOnlyMedBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        medBookStorage.saveMedBook(addressBook, filePath);
    }

}
