package seedu.address.storage;

import seedu.address.model.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a temporary files storage for {@link seedu.address.model.AddressBook}.
 */
public interface TempAddressBookStorage {

    /**
     * Returns the file path of where the temp data files would be stored.
     */
    Path getTempAddressBookFilepath();

    /**
     * Adds a new temporary file of addressBook data.
     *
     * @param addressBook AddressBook data
     * @throws Exception if there was any problem creating a new temp file.
     */
    void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws Exception;

    /**
     * Gets the last added temporary addressBook file data.
     *
     * @return the last added temporary addressBook file data stored.
     * @throws Exception If there are any issues reading data from the temporary file.
     */
    Optional<ReadOnlyAddressBook> popTempAddressFileData() throws Exception;

    /**
     * Deletes temporary files in application.
     * @throws Exception If there are any issues with deleting the files.
     */
    void deleteAllTempFilesData() throws Exception;
}
