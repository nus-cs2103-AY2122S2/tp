package seedu.ibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.ibook.commons.exceptions.DataConversionException;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.ReadOnlyUserPrefs;
import seedu.ibook.model.UserPrefs;

/**
 * API of the Storage component
 */
// TODO : remove the inheritance from AddressBookStorage
public interface Storage extends IBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getIBookFilePath();

    @Override
    Optional<ReadOnlyIBook> readIBook() throws DataConversionException, IOException;

    @Override
    void saveIBook(ReadOnlyIBook iBook) throws IOException;
}
