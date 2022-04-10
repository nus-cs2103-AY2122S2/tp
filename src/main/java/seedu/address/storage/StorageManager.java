package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHireLah;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HireLah data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HireLahStorage hireLahStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HireLahStorage hireLahStorage, UserPrefsStorage userPrefsStorage) {
        this.hireLahStorage = hireLahStorage;
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


    // ================ HireLah methods ==============================

    @Override
    public Path getHireLahFilePath() {
        return hireLahStorage.getHireLahFilePath();
    }

    @Override
    public Optional<ReadOnlyHireLah> readHireLah() throws DataConversionException, IOException {
        return readHireLah(hireLahStorage.getHireLahFilePath());
    }

    @Override
    public Optional<ReadOnlyHireLah> readHireLah(Path filePath) throws DataConversionException, IOException {
        logger.info("Attempting to read data from file: " + filePath);
        return hireLahStorage.readHireLah(filePath);
    }

    @Override
    public void saveHireLah(ReadOnlyHireLah hireLah) throws IOException {
        saveHireLah(hireLah, hireLahStorage.getHireLahFilePath());
    }

    @Override
    public void saveHireLah(ReadOnlyHireLah hireLah, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hireLahStorage.saveHireLah(hireLah, filePath);
    }

}
