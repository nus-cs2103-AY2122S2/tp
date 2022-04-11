package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHustleBook;

/**
 * Represents a storage for {@link seedu.address.model.HustleBook}.
 */
public interface HustleBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHustleBookFilePath();

    /**
     * Returns HustleBook data as a {@link ReadOnlyHustleBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHustleBook> readHustleBook() throws DataConversionException, IOException;

    /**
     * @see #getHustleBookFilePath()
     */
    Optional<ReadOnlyHustleBook> readHustleBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHustleBook} to the storage.
     * @param hustleBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHustleBook(ReadOnlyHustleBook hustleBook) throws IOException;

    /**
     * @see #saveHustleBook(ReadOnlyHustleBook)
     */
    void saveHustleBook(ReadOnlyHustleBook hustleBook, Path filePath) throws IOException;

}
