package woofareyou.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import woofareyou.commons.exceptions.DataConversionException;
import woofareyou.model.ReadOnlyAddressBook;
import woofareyou.model.ReadOnlyUserPrefs;
import woofareyou.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
