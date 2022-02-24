package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyRecipeBook;

/**
 * Represents a storage for {@link seedu.address.model.RecipeBookStorage}.
 */

public interface RecipeBookStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getRecipeBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getRecipeBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readRecipeBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRecipeBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRecipeBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveRecipeBook(ReadOnlyRecipeBook)
     */
    void saveRecipeBook(ReadOnlyRecipeBook addressBook, Path filePath) throws IOException;
}
