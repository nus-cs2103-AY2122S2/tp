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
import seedu.address.model.ReadOnlyExpenseExpert;

/**
 * A class to access ExpenseExpert data stored as a json file on the hard disk.
 */
public class JsonExpenseExpertStorage implements ExpenseExpertStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonExpenseExpertStorage.class);

    private Path filePath;

    public JsonExpenseExpertStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getExpenseExpertFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyExpenseExpert> readExpenseExpert() throws DataConversionException {
        return readExpenseExpert(filePath);
    }

    /**
     * Similar to {@link #readExpenseExpert()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyExpenseExpert> readExpenseExpert(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableExpenseExpert> jsonExpenseExpert = JsonUtil.readJsonFile(
                filePath, JsonSerializableExpenseExpert.class);
        if (!jsonExpenseExpert.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonExpenseExpert.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert) throws IOException {
        saveExpenseExpert(expenseExpert, filePath);
    }

    /**
     * Similar to {@link #saveExpenseExpert(ReadOnlyExpenseExpert)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert, Path filePath) throws IOException {
        requireNonNull(expenseExpert);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableExpenseExpert(expenseExpert), filePath);
    }

}
