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
import seedu.address.model.person.UniqueModuleList;


public class JsonModuleListStorage implements ModuleListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonContactListStorage.class);

    private Path filePath;

    public JsonModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getModuleListFilePath() {
        return filePath;
    }

    @Override
    public Optional<UniqueModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(filePath);
    }

    /**
     * Similar to {@link #readModuleList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<UniqueModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleList> jsonModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleList.class);
        if (!jsonModuleList.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonModuleList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveModuleList(UniqueModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    /**
     * Similar to {@link #saveModuleList(UniqueModuleList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveModuleList(UniqueModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(filePath);
        requireNonNull(moduleList);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(moduleList), filePath);
    }
}

