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
import seedu.address.model.ReadOnlyInternApplyMemory;

/**
 * A class to access InternApply data stored as a json file on the hard disk.
 */
public class JsonInternApplyStorage implements InternApplyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInternApplyStorage.class);

    private Path filePath;

    public JsonInternApplyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInternApplyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInternApplyMemory> readInternApplyMemory() throws DataConversionException {
        return readInternApplyMemory(filePath);
    }

    /**
     * Similar to {@link #readInternApplyMemory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInternApplyMemory> readInternApplyMemory(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInternApply> jsonInternApply = JsonUtil.readJsonFile(
                filePath, JsonSerializableInternApply.class);
        if (!jsonInternApply.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInternApply.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory) throws IOException {
        saveInternApply(internApplyMemory, filePath);
    }

    /**
     * Similar to {@link #saveInternApply(ReadOnlyInternApplyMemory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInternApply(ReadOnlyInternApplyMemory internApplyMemory, Path filePath) throws IOException {
        requireNonNull(internApplyMemory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInternApply(internApplyMemory), filePath);
    }

}
