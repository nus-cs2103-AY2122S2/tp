package seedu.unite.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.Unite;

/**
 * Represents a storage for {@link Unite}.
 */
public interface UniteStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUniteFilePath();

    /**
     * Returns Unite data as a {@link ReadOnlyUnite}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUnite> readUnite() throws DataConversionException, IOException;

    /**
     * @see #getUniteFilePath()
     */
    Optional<ReadOnlyUnite> readUnite(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUnite} to the storage.
     * @param unite cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUnite(ReadOnlyUnite unite) throws IOException;

    /**
     * @see #saveUnite(ReadOnlyUnite)
     */
    void saveUnite(ReadOnlyUnite unite, Path filePath) throws IOException;

}
