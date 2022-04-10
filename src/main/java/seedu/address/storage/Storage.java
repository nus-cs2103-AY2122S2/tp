package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyInsuranceBook;
import seedu.address.model.ReadOnlyRecordBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, InsuranceBookStorage, AppointmentBookStorage,
        RecordBookStorage, UserPrefsStorage {

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


    // ================ AppointmentBook methods ==========================
    @Override
    Path getAppointmentBookFilePath();

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException;

    // ================ InsuranceBook methods ==========================
    @Override
    Path getInsuranceBookFilePath();

    @Override
    Optional<ReadOnlyInsuranceBook> readInsuranceBook() throws DataConversionException, IOException;

    @Override
    void saveInsuranceBook(ReadOnlyInsuranceBook insuranceBook) throws IOException;

    // ================ RecordBook methods ==========================
    @Override
    Path getRecordBookFilePath();

    @Override
    Optional<ReadOnlyRecordBook> readRecordBook() throws DataConversionException, IOException;


    void saveRecordBook(ReadOnlyRecordBook recordBook) throws IOException;
}
