package seedu.unite.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.unite.commons.core.LogsCenter;
import seedu.unite.commons.exceptions.DataConversionException;
import seedu.unite.commons.exceptions.IllegalValueException;
import seedu.unite.commons.util.FileUtil;
import seedu.unite.commons.util.JsonUtil;
import seedu.unite.model.ReadOnlyUnite;

/**
 * A class to access Unite data stored as a json file on the hard disk.
 */
public class JsonUniteStorage implements UniteStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonUniteStorage.class);

    private Path filePath;

    public JsonUniteStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getUniteFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyUnite> readUnite() throws DataConversionException {
        return readUnite(filePath);
    }

    /**
     * Similar to {@link #readUnite()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyUnite> readUnite(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableUnite> jsonUnite = JsonUtil.readJsonFile(
                filePath, JsonSerializableUnite.class);
        if (!jsonUnite.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUnite.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUnite(ReadOnlyUnite unite) throws IOException {
        saveUnite(unite, filePath);
    }

    /**
     * Similar to {@link #saveUnite(ReadOnlyUnite)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveUnite(ReadOnlyUnite unite, Path filePath) throws IOException {
        requireNonNull(unite);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUnite(unite), filePath);
    }

}
