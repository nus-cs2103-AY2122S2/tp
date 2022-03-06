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
import seedu.address.model.ReadOnlyIBook;

/**
 * A class to access IBook data stored as a json file on the hard disk.
 */
public class JsonIBookStorage implements IBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonIBookStorage.class);

    private Path filePath;

    public JsonIBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getIBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyIBook> readIBook() throws DataConversionException {
        return readIBook(filePath);
    }

    /**
     * Similar to {@link #readIBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyIBook> readIBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableIBook> jsonIBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableIBook.class);
        if (!jsonIBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonIBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveIBook(ReadOnlyIBook iBook) throws IOException {
        saveIBook(iBook, filePath);
    }

    /**
     * Similar to {@link #saveIBook(ReadOnlyIBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveIBook(ReadOnlyIBook iBook, Path filePath) throws IOException {
        requireNonNull(iBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableIBook(iBook), filePath);
    }

}
