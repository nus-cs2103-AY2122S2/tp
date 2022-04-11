package unibook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import unibook.commons.core.LogsCenter;
import unibook.commons.exceptions.DataConversionException;
import unibook.commons.exceptions.IllegalValueException;
import unibook.commons.util.FileUtil;
import unibook.commons.util.JsonUtil;
import unibook.model.ReadOnlyUniBook;
import unibook.model.person.exceptions.PersonNoSubtypeException;

/**
 * A class to access UniBook data stored as a json file on the hard disk.
 */
public class JsonUniBookStorage implements UniBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniBookStorage.class);

    private Path filePath;

    public JsonUniBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUniBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUniBook> readUniBook() throws DataConversionException {
        return readUniBook(filePath);
    }

    /**
     * Similar to {@link #readUniBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUniBook> readUniBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUniBook> jsonUniBook = JsonUtil.readJsonFile(
            filePath, JsonSerializableUniBook.class);
        if (!jsonUniBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUniBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        } catch (PersonNoSubtypeException pe) {
            logger.info("Stored person(s) found in " + filePath + ": " + "have no specific Person subtype.");
            throw new DataConversionException(pe);
        }
    }

    @Override
    public void saveUniBook(ReadOnlyUniBook uniBook) throws IOException {
        saveUniBook(uniBook, filePath);
    }

    /**
     * Similar to {@link #saveUniBook(ReadOnlyUniBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUniBook(ReadOnlyUniBook uniBook, Path filePath) throws IOException {
        requireNonNull(uniBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUniBook(uniBook), filePath);
    }

}
