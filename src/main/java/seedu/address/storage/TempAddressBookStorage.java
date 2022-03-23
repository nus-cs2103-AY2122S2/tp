package seedu.address.storage;

import seedu.address.model.ReadOnlyAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public interface TempAddressBookStorage {
    Path getTempAddressBookFilepath();
    void addNewTempAddressBookFile(ReadOnlyAddressBook addressBook) throws IOException;
    Optional<ReadOnlyAddressBook> popTempAddressFileData();
}
