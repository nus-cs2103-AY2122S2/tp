package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MedBook;
import seedu.address.model.ReadOnlyMedBook;

/**
 * Represents a storage for {@link MedBook}.
 */
public interface MedBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMedBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyMedBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedBook> readMedBook() throws DataConversionException, IOException;

    /**
     * @see #getMedBookFilePath()
     */
    Optional<ReadOnlyMedBook> readMedBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedBook(ReadOnlyMedBook addressBook) throws IOException;

    /**
     * @see #saveMedBook(ReadOnlyMedBook)
     */
    void saveMedBook(ReadOnlyMedBook addressBook, Path filePath) throws IOException;

}
