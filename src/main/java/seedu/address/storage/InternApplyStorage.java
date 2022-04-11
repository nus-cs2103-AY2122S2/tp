package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.ReadOnlyInternApplyMemory;

/**
 * Represents a storage for {@link InternApplyMemory}.
 */
public interface InternApplyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternApplyFilePath();

    /**
     * Returns InternApply data as a {@link ReadOnlyInternApplyMemory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternApplyMemory> readInternApplyMemory() throws DataConversionException, IOException;

    /**
     * @see #getInternApplyFilePath()
     */
    Optional<ReadOnlyInternApplyMemory> readInternApplyMemory(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInternApplyMemory} to the storage.
     * @param internApplyMemory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory) throws IOException;

    /**
     * @see #saveInternApply(ReadOnlyInternApplyMemory)
     */
    void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory, Path filePath) throws IOException;

}
