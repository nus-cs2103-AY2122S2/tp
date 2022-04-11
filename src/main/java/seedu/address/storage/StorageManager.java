package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of LinkyTime data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private LinkyTimeStorage linkyTimeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LinkyTimeStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LinkyTimeStorage linkyTimeStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.userPrefsStorage = userPrefsStorage;
        this.linkyTimeStorage = linkyTimeStorage;
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

    // ================ LinkyTime methods ==============================

    @Override
    public Path getLinkyTimeFilePath() {
        return linkyTimeStorage.getLinkyTimeFilePath();
    }

    @Override
    public Optional<ReadOnlyLinkyTime> readLinkyTime() throws DataConversionException, IOException {
        return readLinkyTime(linkyTimeStorage.getLinkyTimeFilePath());
    }

    @Override
    public Optional<ReadOnlyLinkyTime> readLinkyTime(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return linkyTimeStorage.readLinkyTime(filePath);
    }

    @Override
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime) throws IOException {
        saveLinkyTime(linkyTime, linkyTimeStorage.getLinkyTimeFilePath());
    }

    @Override
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        linkyTimeStorage.saveLinkyTime(linkyTime, filePath);
    }

}
