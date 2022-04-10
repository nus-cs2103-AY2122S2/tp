package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRecordBook;

public interface RecordBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRecordBookFilePath();

    /**
     * Returns RecordBook data as a {@link ReadOnlyRecordBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRecordBook> readRecordBook() throws DataConversionException, IOException;

    /**
     * @see #getRecordBookFilePath()
     */
    Optional<ReadOnlyRecordBook> readRecordBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRecordBook} to the storage.
     * @param recordBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRecordBook(ReadOnlyRecordBook recordBook) throws IOException;

    /**
     * @see #saveRecordBook(ReadOnlyRecordBook)
     */
    void saveRecordBook(ReadOnlyRecordBook recordBook, Path filePath) throws IOException;
}
