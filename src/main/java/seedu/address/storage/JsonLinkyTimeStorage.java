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
import seedu.address.model.ReadOnlyLinkyTime;

/**
 * A class to access LinkyTime data stored as a json file on the hard disk.
 */
public class JsonLinkyTimeStorage implements LinkyTimeStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonLinkyTimeStorage.class);

    // Temporary dummy data to substitute data file from storage while this class is still being implemented.

    private final Path filePath;

    public JsonLinkyTimeStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLinkyTimeFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLinkyTime> readLinkyTime() throws DataConversionException {
        return readLinkyTime(filePath);
    }

    /**
     * Similar to {@link #readLinkyTime()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLinkyTime> readLinkyTime(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        final Optional<JsonSerializableLinkyTime> jsonLinkyTime = JsonUtil.readJsonFile(
                filePath, JsonSerializableLinkyTime.class);
        if (!jsonLinkyTime.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLinkyTime.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime) throws IOException {
        saveLinkyTime(linkyTime, filePath);
    }

    /**
     * Similar to {@link #saveLinkyTime(ReadOnlyLinkyTime)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLinkyTime(ReadOnlyLinkyTime linkyTime, Path filePath) throws IOException {
        requireNonNull(linkyTime);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLinkyTime(linkyTime), filePath);
    }

}
