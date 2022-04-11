package seedu.contax.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.contax.commons.exceptions.DataConversionException;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.ReadOnlyUserPrefs;
import seedu.contax.model.UserPrefs;

/**
 * Defines the API of the Storage component.
 */
public interface Storage extends AddressBookStorage, ScheduleStorage, UserPrefsStorage {

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

    @Override
    Path getScheduleFilePath();

    @Override
    Optional<ReadOnlySchedule> readSchedule(ReadOnlyAddressBook addressbook)
            throws DataConversionException, IOException;

    @Override
    void saveSchedule(ReadOnlySchedule schedule) throws IOException;

}
