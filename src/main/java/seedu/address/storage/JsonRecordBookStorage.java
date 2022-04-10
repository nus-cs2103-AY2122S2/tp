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
import seedu.address.model.ReadOnlyRecordBook;

/**
 * A class to access Record data stored as a json file on the hard disk.
 */
public class JsonRecordBookStorage implements RecordBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonRecordBookStorage.class);

    private Path filePath;

    public JsonRecordBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getRecordBookFilePath() {
        return filePath;
    }


    @Override
    public Optional<ReadOnlyRecordBook> readRecordBook() throws DataConversionException, IOException {
        return readRecordBook(filePath);
    }

    /**
     * Similar to {@link #readRecordBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyRecordBook> readRecordBook(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableRecordBook> jsonRecordBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableRecordBook.class);
        if (!jsonRecordBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRecordBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRecordBook(ReadOnlyRecordBook recordBook) throws IOException {
        saveRecordBook(recordBook, filePath);
    }

    @Override
    public void saveRecordBook(ReadOnlyRecordBook recordBook, Path filePath) throws IOException {
        requireNonNull(recordBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRecordBook(recordBook), filePath);
    }
}
