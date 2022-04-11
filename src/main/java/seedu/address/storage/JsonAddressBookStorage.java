package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private final Path mainFilePath;
    private Path backupFilePath;

    public JsonAddressBookStorage(Path filePath) {
        this.mainFilePath = filePath;
    }

    /**
     * Constructs an AddressBookStorage that has 2 file paths initialized. One is for the main file path where the
     * addressbook.json file is located and another is the file path where the backup.json file is located. The
     * backup file is created when users
     *
     * @param mainFilePath Main file path that stores addressbok.json.
     * @param backupFilePath Backup file path that stores backup.json.
     */
    public JsonAddressBookStorage(Path mainFilePath, Path backupFilePath) {
        this.mainFilePath = mainFilePath;
        this.backupFilePath = backupFilePath;
    }

    public Path getAddressBookFilePath() {
        return mainFilePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException {
        return readAddressBook(mainFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAddressBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableAddressBook.class);
        if (jsonAddressBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            logger.info("Attempting to backup file to: " + backupFilePath);
            backUpFile(mainFilePath, backupFilePath);
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, mainFilePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }

    /**
     * Stores the user corrupted addressbook.json file content so that even after re-loading the app
     * and the addressbok.json file becomes empty, user will still be able to retrieve their previous
     * AddressBook data in the backup file.
     *
     * @param copyFrom  File path to addressbook.json so that the method can copy the content from it.
     * @param copyTo File path to backup.json so that the method can copy the content to it.
     */
    private void backUpFile(Path copyFrom, Path copyTo) {
        if (copyTo != null) {
            try {
                Files.copy(copyFrom, copyTo);
                logger.info("Backup successful.");
            } catch (IOException exception) {
                logger.info("Unable to backup file to: " + copyTo);
            }
        }
    }
}
