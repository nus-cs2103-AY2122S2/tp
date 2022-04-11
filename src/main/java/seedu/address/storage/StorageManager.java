package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of InternApply data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternApplyStorage internApplyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternApplyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternApplyStorage internApplyStorage, UserPrefsStorage userPrefsStorage) {
        this.internApplyStorage = internApplyStorage;
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
    public Path getInternApplyFilePath() {
        return internApplyStorage.getInternApplyFilePath();
    }

    @Override
    public Optional<ReadOnlyInternApplyMemory> readInternApplyMemory() throws DataConversionException, IOException {
        return readInternApplyMemory(internApplyStorage.getInternApplyFilePath());
    }

    @Override
    public Optional<ReadOnlyInternApplyMemory> readInternApplyMemory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internApplyStorage.readInternApplyMemory(filePath);
    }

    @Override
    public void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory) throws IOException {
        saveInternApply(internApplyMemory, internApplyStorage.getInternApplyFilePath());
    }

    @Override
    public void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internApplyStorage.saveInternApply(internApplyMemory, filePath);
    }

}
