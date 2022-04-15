package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMedBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonMedBookStorage implements MedBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMedBookStorage.class);

    private final Path filePath;

    public JsonMedBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMedBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedBook> readMedBook() throws DataConversionException {
        return readMedBook(filePath);
    }

    /**
     * Similar to {@link #readMedBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMedBook> readMedBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMedBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableMedBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());

        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMedBook(ReadOnlyMedBook addressBook) throws IOException {
        saveMedBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveMedBook(ReadOnlyMedBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMedBook(ReadOnlyMedBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMedBook(addressBook), filePath);
    }

}
