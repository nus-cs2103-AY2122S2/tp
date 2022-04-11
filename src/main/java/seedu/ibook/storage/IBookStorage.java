package seedu.ibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ibook.commons.exceptions.DataConversionException;
import seedu.ibook.model.ReadOnlyIBook;

/**
 * Represents a storage for {@link seedu.ibook.model.IBook}.
 */
public interface IBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getIBookFilePath();

    /**
     * Returns IBook data as a {@link ReadOnlyIBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyIBook> readIBook() throws DataConversionException, IOException;

    /**
     * @see #getIBookFilePath()
     */
    Optional<ReadOnlyIBook> readIBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyIBook} to the storage.
     * @param iBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveIBook(ReadOnlyIBook iBook) throws IOException;

    /**
     * @see #saveIBook(ReadOnlyIBook)
     */
    void saveIBook(ReadOnlyIBook iBook, Path filePath) throws IOException;

}
