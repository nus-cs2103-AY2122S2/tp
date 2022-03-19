package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHustleBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HustleBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HustleBookStorage hustleBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HustleBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HustleBookStorage hustleBookStorage, UserPrefsStorage userPrefsStorage) {
        this.hustleBookStorage = hustleBookStorage;
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


    // ================ HustleBook methods ==============================

    @Override
    public Path getHustleBookFilePath() {
        return hustleBookStorage.getHustleBookFilePath();
    }

    @Override
    public Optional<ReadOnlyHustleBook> readHustleBook() throws DataConversionException, IOException {
        return readHustleBook(hustleBookStorage.getHustleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyHustleBook> readHustleBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hustleBookStorage.readHustleBook(filePath);
    }

    @Override
    public void saveHustleBook(ReadOnlyHustleBook hustleBook) throws IOException {
        saveHustleBook(hustleBook, hustleBookStorage.getHustleBookFilePath());
    }

    @Override
    public void saveHustleBook(ReadOnlyHustleBook hustleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hustleBookStorage.saveHustleBook(hustleBook, filePath);
    }

}
