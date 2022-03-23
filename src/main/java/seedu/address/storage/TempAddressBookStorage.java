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
     * @throws IOException if there was any problem adding a new temp file to storage.
     */
    void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * Gets the last added temporary addressBook file data.
     *
     * @return the last added temporary addressBook file data stored.
     */
    Optional<ReadOnlyAddressBook> popTempAddressFileData();
}
