package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTAssist;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of TAssist data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TAssistStorage tAssistStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code tAssistStorage} and {@code UserPrefStorage}.
     * TODO: to be updated
     */
    public StorageManager(TAssistStorage tAssistStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.userPrefsStorage = userPrefsStorage;
        this.tAssistStorage = tAssistStorage;
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

    // ================ TAssist methods ==============================

    @Override
    public Path getTAssistFilePath() {
        return tAssistStorage.getTAssistFilePath();
    }

    @Override
    public Optional<ReadOnlyTAssist> readTAssist() throws DataConversionException, IOException {
        return readTAssist(tAssistStorage.getTAssistFilePath());
    }

    @Override
    public Optional<ReadOnlyTAssist> readTAssist(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tAssistStorage.readTAssist(filePath);
    }

    @Override
    public void saveTAssist(ReadOnlyTAssist tAssist) throws IOException {
        saveTAssist(tAssist, tAssistStorage.getTAssistFilePath());
    }

    @Override
    public void saveTAssist(ReadOnlyTAssist tAssist, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tAssistStorage.saveTAssist(tAssist, filePath);
    }

}
