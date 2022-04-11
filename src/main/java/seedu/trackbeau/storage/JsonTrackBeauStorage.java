package seedu.trackbeau.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.commons.exceptions.DataConversionException;
import seedu.trackbeau.commons.exceptions.IllegalValueException;
import seedu.trackbeau.commons.util.FileUtil;
import seedu.trackbeau.commons.util.JsonUtil;
import seedu.trackbeau.model.ReadOnlyTrackBeau;

/**
 * A class to access TrackBeau data stored as a json file on the hard disk.
 */
public class JsonTrackBeauStorage implements TrackBeauStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTrackBeauStorage.class);

    private Path filePath;

    public JsonTrackBeauStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTrackBeauFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTrackBeau> readTrackBeau() throws DataConversionException {
        return readTrackBeau(filePath);
    }

    /**
     * Similar to {@link #readTrackBeau()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTrackBeau> readTrackBeau(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTrackBeau> jsonTrackBeau = JsonUtil.readJsonFile(
                filePath, JsonSerializableTrackBeau.class);
        if (!jsonTrackBeau.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTrackBeau.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTrackBeau(ReadOnlyTrackBeau trackBeau) throws IOException {
        saveTrackBeau(trackBeau, filePath);
    }

    /**
     * Similar to {@link #saveTrackBeau(ReadOnlyTrackBeau)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTrackBeau(ReadOnlyTrackBeau trackBeau, Path filePath) throws IOException {
        requireNonNull(trackBeau);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTrackBeau(trackBeau), filePath);
    }

}
