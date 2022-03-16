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
import seedu.address.model.ReadOnlyInterviewSchedule;

/**
 * A class to access InterviewSchedule data stored as a json file on the hard disk.
 */
public class JsonInterviewListStorage implements InterviewListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInterviewListStorage.class);

    private Path filePath;

    public JsonInterviewListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInterviewListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyInterviewSchedule> readInterviewList() throws DataConversionException {
        return readInterviewList(filePath);
    }

    /**
     * Similar to {@link #readInterviewList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInterviewSchedule> readInterviewList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInterviewList> jsonInterviewList = JsonUtil.readJsonFile(
                filePath, JsonSerializableInterviewList.class);
        if (!jsonInterviewList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonInterviewList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInterviewList(ReadOnlyInterviewSchedule InterviewList) throws IOException {
        saveInterviewList(InterviewList, filePath);
    }

    /**
     * Similar to {@link #saveInterviewList(ReadOnlyInterviewSchedule)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInterviewList(ReadOnlyInterviewSchedule InterviewList, Path filePath) throws IOException {
        requireNonNull(InterviewList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInterviewList(InterviewList), filePath);
    }

}
