package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.OldReadOnlyUserPrefs;
import seedu.address.model.OldUserPrefs;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyIBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
// TODO : remove the inheritance from AddressBookStorage
public interface Storage extends AddressBookStorage, IBookStorage, UserPrefsStorage {

    @Override
    Optional<OldUserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(OldReadOnlyUserPrefs userPrefs) throws IOException;

    // TODO : delete start
    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;
    // TODO : delete end

    @Override
    Path getIBookFilePath();

    @Override
    Optional<ReadOnlyIBook> readIBook() throws DataConversionException, IOException;

    @Override
    void saveIBook(ReadOnlyIBook iBook) throws IOException;
}
