package seedu.address.logic.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyShowList;
import seedu.address.model.ReadOnlyUserPrefsTrackermon;
import seedu.address.model.UserPrefsTrackermon;

/**
 * Manages storage of ShowList data in local storage.
 */
public class StorageManagerTrackermon implements StorageTrackermon {

    private static final Logger logger = LogsCenter.getLogger(StorageManagerTrackermon.class);
    private ShowListStorage showListStorage;
    private UserPrefsStorageTrackermon userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManagerTrackermon(ShowListStorage showListStorage, UserPrefsStorageTrackermon userPrefsStorage) {
        this.showListStorage = showListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefsTrackermon> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefsTrackermon userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ShowList methods ==============================

    @Override
    public Path getShowListFilePath() {
        return showListStorage.getShowListFilePath();
    }

    @Override
    public Optional<ReadOnlyShowList> readShowList() throws DataConversionException, IOException {
        return readShowList(showListStorage.getShowListFilePath());
    }

    @Override
    public Optional<ReadOnlyShowList> readShowList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return showListStorage.readShowList(filePath);
    }

    @Override
    public void saveShowList(ReadOnlyShowList showList) throws IOException {
        saveShowList(showList, showListStorage.getShowListFilePath());
    }

    @Override
    public void saveShowList(ReadOnlyShowList showList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        showListStorage.saveShowList(showList, filePath);
    }

}
