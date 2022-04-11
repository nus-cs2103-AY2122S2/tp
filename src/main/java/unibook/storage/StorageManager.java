package unibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import unibook.commons.core.LogsCenter;
import unibook.commons.exceptions.DataConversionException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.ReadOnlyUserPrefs;
import unibook.model.UserPrefs;

/**
 * Manages storage of UniBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UniBookStorage uniBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UniBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UniBookStorage uniBookStorage, UserPrefsStorage userPrefsStorage) {
        this.uniBookStorage = uniBookStorage;
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


    // ================ UniBook methods ==============================

    @Override
    public Path getUniBookFilePath() {
        return uniBookStorage.getUniBookFilePath();
    }

    @Override
    public Optional<ReadOnlyUniBook> readUniBook() throws DataConversionException, IOException {
        return readUniBook(uniBookStorage.getUniBookFilePath());
    }

    @Override
    public Optional<ReadOnlyUniBook> readUniBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return uniBookStorage.readUniBook(filePath);
    }

    @Override
    public void saveUniBook(ReadOnlyUniBook uniBook) throws IOException {
        saveUniBook(uniBook, uniBookStorage.getUniBookFilePath());
    }

    @Override
    public void saveUniBook(ReadOnlyUniBook uniBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        uniBookStorage.saveUniBook(uniBook, filePath);
    }

}
