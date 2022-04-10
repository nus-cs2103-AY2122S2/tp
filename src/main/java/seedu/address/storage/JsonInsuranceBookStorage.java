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
import seedu.address.model.ReadOnlyInsuranceBook;

/**
 * A class to access Insurance data stored as a json file on the hard disk.
 */
public class JsonInsuranceBookStorage implements InsuranceBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInsuranceBookStorage.class);

    private Path filePath;

    public JsonInsuranceBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInsuranceBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInsuranceBook> readInsuranceBook() throws DataConversionException {
        return readInsuranceBook(filePath);
    }

    /**
     * Similar to {@link #readInsuranceBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInsuranceBook> readInsuranceBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInsuranceBook> jsonInsuranceBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableInsuranceBook.class);
        if (!jsonInsuranceBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInsuranceBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInsuranceBook(ReadOnlyInsuranceBook addressBook) throws IOException {
        saveInsuranceBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveInsuranceBook(ReadOnlyInsuranceBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInsuranceBook(ReadOnlyInsuranceBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInsuranceBook(addressBook), filePath);
    }

}
