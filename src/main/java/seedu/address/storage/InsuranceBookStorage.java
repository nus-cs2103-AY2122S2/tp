package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInsuranceBook;

/**
 * Represents a storage for {@link seedu.address.model.InsuranceBook}.
 */
public interface InsuranceBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInsuranceBookFilePath();

    /**
     * Returns InsuranceBook data as a {@link ReadOnlyInsuranceBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInsuranceBook> readInsuranceBook() throws DataConversionException, IOException;

    /**
     * @see #getInsuranceBookFilePath()
     */
    Optional<ReadOnlyInsuranceBook> readInsuranceBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInsuranceBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInsuranceBook(ReadOnlyInsuranceBook addressBook) throws IOException;

    /**
     * @see #saveInsuranceBook(ReadOnlyInsuranceBook)
     */
    void saveInsuranceBook(ReadOnlyInsuranceBook addressBook, Path filePath) throws IOException;

}
