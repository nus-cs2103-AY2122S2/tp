package seedu.ibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ibook.commons.core.LogsCenter;
import seedu.ibook.commons.exceptions.DataConversionException;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.ReadOnlyUserPrefs;
import seedu.ibook.model.UserPrefs;

// import seedu.ibook.model.ReadOnlyUserPrefs;
// import seedu.ibook.model.UserPrefs;

/**
 * Manages storage of IBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private IBookStorage iBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code IBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(IBookStorage iBookStorage, UserPrefsStorage userPrefsStorage) {
        this.iBookStorage = iBookStorage;
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

    // ================ IBook methods ==============================

    @Override
    public Path getIBookFilePath() {
        return iBookStorage.getIBookFilePath();
    }

    @Override
    public Optional<ReadOnlyIBook> readIBook() throws DataConversionException, IOException {
        return readIBook(iBookStorage.getIBookFilePath());
    }

    @Override
    public Optional<ReadOnlyIBook> readIBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return iBookStorage.readIBook(filePath);
    }

    @Override
    public void saveIBook(ReadOnlyIBook iBook) throws IOException {
        saveIBook(iBook, iBookStorage.getIBookFilePath());
    }

    @Override
    public void saveIBook(ReadOnlyIBook iBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        iBookStorage.saveIBook(iBook, filePath);
    }
}
