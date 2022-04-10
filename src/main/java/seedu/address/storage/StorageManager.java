package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private InsuranceBookStorage insuranceBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private AppointmentBookStorage appointmentBookStorage;
    private RecordBookStorage recordBookStorage;


    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, InsuranceBookStorage insuranceBookStorage,
                          AppointmentBookStorage appointmentBookStorage, RecordBookStorage recordBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.insuranceBookStorage = insuranceBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.appointmentBookStorage = appointmentBookStorage;
        this.recordBookStorage = recordBookStorage;
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

    // ================ AppointmentBook methods ==========================
    @Override
    public Path getAppointmentBookFilePath() {
        return appointmentBookStorage.getAppointmentBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException {
        return readAppointmentBook(appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAppointmentBook> readAppointmentBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return appointmentBookStorage.readAppointmentBook(filePath);
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException {
        saveAppointmentBook(appointmentBook, appointmentBookStorage.getAppointmentBookFilePath());
    }

    @Override
    public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to appointment data file: " + filePath);
        appointmentBookStorage.saveAppointmentBook(appointmentBook, filePath);
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
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ InsuranceBook methods ==============================

    @Override
    public Path getInsuranceBookFilePath() {
        return insuranceBookStorage.getInsuranceBookFilePath();
    }

    @Override
    public Optional<ReadOnlyInsuranceBook> readInsuranceBook() throws DataConversionException, IOException {
        return readInsuranceBook(insuranceBookStorage.getInsuranceBookFilePath());
    }

    @Override
    public Optional<ReadOnlyInsuranceBook> readInsuranceBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return insuranceBookStorage.readInsuranceBook(filePath);
    }

    @Override
    public void saveInsuranceBook(ReadOnlyInsuranceBook insuranceBook) throws IOException {
        saveInsuranceBook(insuranceBook, insuranceBookStorage.getInsuranceBookFilePath());
    }

    @Override
    public void saveInsuranceBook(ReadOnlyInsuranceBook insuranceBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        insuranceBookStorage.saveInsuranceBook(insuranceBook, filePath);
    }

    // ================ RecordBook methods ==============================

    @Override
    public Path getRecordBookFilePath() {
        return recordBookStorage.getRecordBookFilePath();
    }

    @Override
    public Optional<ReadOnlyRecordBook> readRecordBook() throws DataConversionException, IOException {
        return readRecordBook(recordBookStorage.getRecordBookFilePath());
    }

    @Override
    public Optional<ReadOnlyRecordBook> readRecordBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return recordBookStorage.readRecordBook(filePath);
    }

    @Override
    public void saveRecordBook(ReadOnlyRecordBook recordBook) throws IOException {
        saveRecordBook(recordBook, recordBookStorage.getRecordBookFilePath());
    }

    @Override
    public void saveRecordBook(ReadOnlyRecordBook recordBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        recordBookStorage.saveRecordBook(recordBook, filePath);
    }

}
