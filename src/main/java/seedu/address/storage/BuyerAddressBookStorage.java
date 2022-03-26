package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBuyerAddressBook;

public interface BuyerAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBuyerAddressBookFilePath();

    /**
     * Returns BuyerAddressBook data as a {@link ReadOnlyBuyerAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getBuyerAddressBookFilePath()
     */
    Optional<ReadOnlyBuyerAddressBook> readBuyerAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBuyerAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook) throws IOException;

    /**
     * @see #saveBuyerAddressBook(ReadOnlyBuyerAddressBook)
     */
    void saveBuyerAddressBook(ReadOnlyBuyerAddressBook addressBook, Path filePath) throws IOException;
}
