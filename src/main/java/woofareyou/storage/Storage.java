package woofareyou.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.ReadOnlyUserPrefs;
import woofareyou.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PetBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPetBookFilePath();

    @Override
    Optional<ReadOnlyPetBook> readPetBook() throws DataConversionException, IOException;

    @Override
    void savePetBook(ReadOnlyPetBook petBook) throws IOException;

}
