package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HireLah;
import seedu.address.model.ReadOnlyHireLah;

/**
 * Represents a storage for {@link HireLah}.
 */
public interface HireLahStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHireLahFilePath();

    /**
     * Returns HireLah data as a {@link ReadOnlyHireLah}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHireLah> readHireLah() throws DataConversionException, IOException;

    /**
     * @see #getHireLahFilePath()
     */
    Optional<ReadOnlyHireLah> readHireLah(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHireLah} to the storage.
     * @param hireLah cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHireLah(ReadOnlyHireLah hireLah) throws IOException;

    /**
     * @see #saveHireLah(ReadOnlyHireLah)
     */
    void saveHireLah(ReadOnlyHireLah hireLah, Path filePath) throws IOException;

}
