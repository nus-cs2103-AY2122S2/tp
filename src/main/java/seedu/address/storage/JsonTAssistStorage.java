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
import seedu.address.model.ReadOnlyTAssist;

/**
 * A class to access TAssist data stored as a json file on the hard disk.
 */
public class JsonTAssistStorage implements TAssistStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTAssistStorage.class);

    private Path filePath;

    public JsonTAssistStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTAssistFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTAssist> readTAssist() throws DataConversionException {
        return readTAssist(filePath);
    }

    /**
     * Similar to {@link #readTAssist()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTAssist> readTAssist(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTAssist> jsonTAssist = JsonUtil.readJsonFile(
                filePath, JsonSerializableTAssist.class);
        if (jsonTAssist.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTAssist.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTAssist(ReadOnlyTAssist tAssist) throws IOException {
        saveTAssist(tAssist, filePath);
    }

    /**
     * Similar to {@link #saveTAssist(ReadOnlyTAssist)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTAssist(ReadOnlyTAssist tAssist, Path filePath) throws IOException {
        requireNonNull(tAssist);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTAssist(tAssist), filePath);
    }

}
