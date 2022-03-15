package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyInterviewList;
import seedu.address.model.ReadOnlyInterviewList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access InterviewList data stored as a json file on the hard disk.
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
    public Optional<ReadOnlyInterviewList> readInterviewList() throws DataConversionException {
        return readInterviewList(filePath);
    }

    /**
     * Similar to {@link #readInterviewList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInterviewList> readInterviewList(Path filePath) throws DataConversionException {
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
    public void saveInterviewList(ReadOnlyInterviewList InterviewList) throws IOException {
        saveInterviewList(InterviewList, filePath);
    }

    /**
     * Similar to {@link #saveInterviewList(ReadOnlyInterviewList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInterviewList(ReadOnlyInterviewList InterviewList, Path filePath) throws IOException {
        requireNonNull(InterviewList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInterviewList(InterviewList), filePath);
    }

}
