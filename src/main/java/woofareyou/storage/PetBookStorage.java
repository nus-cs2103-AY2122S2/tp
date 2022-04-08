package woofareyou.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;

/**
 * Represents a storage for {@link PetBook}.
 */
public interface PetBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPetBookFilePath();

    /**
     * Returns PetBook data as a {@link ReadOnlyPetBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPetBook> readPetBook() throws DataConversionException, IOException;

    /**
     * @see #getPetBookFilePath()
     */
    Optional<ReadOnlyPetBook> readPetBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPetBook} to the storage.
     * @param petBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePetBook(ReadOnlyPetBook petBook) throws IOException;

    /**
     * @see #savePetBook(ReadOnlyPetBook)
     */
    void savePetBook(ReadOnlyPetBook petBook, Path filePath) throws IOException;

}
