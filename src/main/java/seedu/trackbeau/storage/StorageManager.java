package seedu.trackbeau.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.commons.exceptions.DataConversionException;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.ReadOnlyUserPrefs;
import seedu.trackbeau.model.UserPrefs;

/**
 * Manages storage of TrackBeau data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackBeauStorage trackBeauStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackBeauStorage trackBeauStorage, UserPrefsStorage userPrefsStorage) {
        this.trackBeauStorage = trackBeauStorage;
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


    // ================ TrackBeau methods ==============================

    @Override
    public Path getTrackBeauFilePath() {
        return trackBeauStorage.getTrackBeauFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackBeau> readTrackBeau() throws DataConversionException, IOException {
        return readTrackBeau(trackBeauStorage.getTrackBeauFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackBeau> readTrackBeau(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackBeauStorage.readTrackBeau(filePath);
    }

    @Override
    public void saveTrackBeau(ReadOnlyTrackBeau trackBeau) throws IOException {
        saveTrackBeau(trackBeau, trackBeauStorage.getTrackBeauFilePath());
    }

    @Override
    public void saveTrackBeau(ReadOnlyTrackBeau trackBeau, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackBeauStorage.saveTrackBeau(trackBeau, filePath);
    }

}
