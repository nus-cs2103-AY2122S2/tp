package seedu.unite.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.unite.commons.core.LogsCenter;
import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.ReadOnlyUserPrefs;
import seedu.unite.model.UserPrefs;

/**
 * Manages storage of Unite data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UniteStorage uniteStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UniteStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(UniteStorage uniteStorage, UserPrefsStorage userPrefsStorage) {
        this.uniteStorage = uniteStorage;
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


    // ================ Unite methods ==============================

    @Override
    public Path getUniteFilePath() {
        return uniteStorage.getUniteFilePath();
    }

    @Override
    public Optional<ReadOnlyUnite> readUnite() throws DataConversionException, IOException {
        return readUnite(uniteStorage.getUniteFilePath());
    }

    @Override
    public Optional<ReadOnlyUnite> readUnite(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return uniteStorage.readUnite(filePath);
    }

    @Override
    public void saveUnite(ReadOnlyUnite unite) throws IOException {
        saveUnite(unite, uniteStorage.getUniteFilePath());
    }

    @Override
    public void saveUnite(ReadOnlyUnite unite, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        uniteStorage.saveUnite(unite, filePath);
    }

}
