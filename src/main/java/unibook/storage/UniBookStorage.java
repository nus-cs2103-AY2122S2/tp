package unibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import unibook.commons.exceptions.DataConversionException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;

/**
 * Represents a storage for {@link UniBook}.
 */
public interface UniBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUniBookFilePath();

    /**
     * Returns UniBook data as a {@link ReadOnlyUniBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUniBook> readUniBook() throws DataConversionException, IOException;

    /**
     * @see #getUniBookFilePath()
     */
    Optional<ReadOnlyUniBook> readUniBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUniBook} to the storage.
     *
     * @param uniBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUniBook(ReadOnlyUniBook uniBook) throws IOException;

    /**
     * @see #saveUniBook(ReadOnlyUniBook)
     */
    void saveUniBook(ReadOnlyUniBook uniBook, Path filePath) throws IOException;

}
