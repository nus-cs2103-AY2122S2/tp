package seedu.trackbeau.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.trackbeau.commons.exceptions.DataConversionException;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.TrackBeau;

/**
 * Represents a storage for {@link TrackBeau}.
 */
public interface TrackBeauStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackBeauFilePath();

    /**
     * Returns TrackBeau data as a {@link ReadOnlyTrackBeau}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTrackBeau> readTrackBeau() throws DataConversionException, IOException;

    /**
     * @see #getTrackBeauFilePath()
     */
    Optional<ReadOnlyTrackBeau> readTrackBeau(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTrackBeau} to the storage.
     * @param trackBeau cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackBeau(ReadOnlyTrackBeau trackBeau) throws IOException;

    /**
     * @see #saveTrackBeau(ReadOnlyTrackBeau)
     */
    void saveTrackBeau(ReadOnlyTrackBeau trackBeau, Path filePath) throws IOException;

}
