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
import seedu.address.model.ReadOnlyHustleBook;

/**
 * A class to access HustleBook data stored as a json file on the hard disk.
 */
public class JsonHustleBookStorage implements HustleBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHustleBookStorage.class);

    private Path filePath;

    public JsonHustleBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHustleBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHustleBook> readHustleBook() throws DataConversionException {
        return readHustleBook(filePath);
    }

    /**
     * Similar to {@link #readHustleBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHustleBook> readHustleBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHustleBook> jsonHustleBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableHustleBook.class);
        if (!jsonHustleBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHustleBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHustleBook(ReadOnlyHustleBook hustleBook) throws IOException {
        saveHustleBook(hustleBook, filePath);
    }

    /**
     * Similar to {@link #saveHustleBook(ReadOnlyHustleBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHustleBook(ReadOnlyHustleBook hustleBook, Path filePath) throws IOException {
        requireNonNull(hustleBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHustleBook(hustleBook), filePath);
    }

}
