package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTAssist;

/**
 * Represents a storage for {@link seedu.address.model.TAssist}.
 */
public interface TAssistStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTAssistFilePath();

    /**
     * Returns TAssist data as a {@link ReadOnlyTAssist}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTAssist> readTAssist() throws DataConversionException, IOException;

    /**
     * @see #getTAssistFilePath()
     */
    Optional<ReadOnlyTAssist> readTAssist(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTAssist} to the storage.
     * @param tAssist cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTAssist(ReadOnlyTAssist tAssist) throws IOException;

    /**
     * @see #saveTAssist(ReadOnlyTAssist)
     */
    void saveTAssist(ReadOnlyTAssist tAssist, Path filePath) throws IOException;

}
