package unibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import unibook.commons.exceptions.DataConversionException;
import unibook.model.ReadOnlyUniBook;
import unibook.model.ReadOnlyUserPrefs;
import unibook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UniBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getUniBookFilePath();

    @Override
    Optional<ReadOnlyUniBook> readUniBook() throws DataConversionException, IOException;

    @Override
    void saveUniBook(ReadOnlyUniBook uniBook) throws IOException;

}
