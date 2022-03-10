package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefsTrackermon;
import seedu.address.model.UserPrefsTrackermon;

/**
 * Represents a storage for {@link UserPrefsTrackermon}.
 */
public interface UserPrefsStorageTrackermon {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserPrefsTrackermon> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUserPrefsTrackermon} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyUserPrefsTrackermon userPrefs) throws IOException;

}
