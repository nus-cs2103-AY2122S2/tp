package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyLinkyTime;

/**
 * Represents a storage for {@link seedu.address.model.LinkyTime}.
 */
public interface LinkyTimeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLinkyTimeFilePath();

    /**
     * Returns LinkyTime data as a {@link ReadOnlyLinkyTime}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLinkyTime> readLinkyTime() throws DataConversionException, IOException;

    /**
     * @see #getLinkyTimeFilePath()
     */
    Optional<ReadOnlyLinkyTime> readLinkyTime(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLinkyTime} to the storage.
     * @param linkyTime cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLinkyTime(ReadOnlyLinkyTime linkyTime) throws IOException;

    /**
     * @see #saveLinkyTime(ReadOnlyLinkyTime)
     */
    void saveLinkyTime(ReadOnlyLinkyTime linkyTime, Path filePath) throws IOException;

}
