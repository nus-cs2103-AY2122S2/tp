package seedu.contax.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.exceptions.DataConversionException;
import seedu.contax.model.ReadOnlyAddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.ReadOnlyUserPrefs;
import seedu.contax.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private ScheduleStorage scheduleStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}, {@code ScheduleStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, ScheduleStorage scheduleStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.scheduleStorage = scheduleStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from address book file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to address book data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ Schedule methods ==============================

    @Override
    public Path getScheduleFilePath() {
        return scheduleStorage.getScheduleFilePath();
    }

    @Override
    public Optional<ReadOnlySchedule> readSchedule(ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException {
        return readSchedule(scheduleStorage.getScheduleFilePath(), addressBook);
    }

    @Override
    public Optional<ReadOnlySchedule> readSchedule(Path filePath, ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from schedule file: " + filePath);
        return scheduleStorage.readSchedule(filePath, addressBook);
    }

    @Override
    public void saveSchedule(ReadOnlySchedule schedule) throws IOException {
        saveSchedule(schedule, scheduleStorage.getScheduleFilePath());
    }

    @Override
    public void saveSchedule(ReadOnlySchedule schedule, Path filePath) throws IOException {
        scheduleStorage.saveSchedule(schedule, filePath);
    }
}
