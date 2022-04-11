package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ExpenseExpert data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExpenseExpertStorage expenseExpertStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExpenseExpertStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ExpenseExpertStorage expenseExpertStorage, UserPrefsStorage userPrefsStorage) {
        this.expenseExpertStorage = expenseExpertStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ExpenseExpert methods ==============================

    @Override
    public Path getExpenseExpertFilePath() {
        return expenseExpertStorage.getExpenseExpertFilePath();
    }

    @Override
    public Optional<ReadOnlyExpenseExpert> readExpenseExpert() throws DataConversionException, IOException {
        return readExpenseExpert(expenseExpertStorage.getExpenseExpertFilePath());
    }

    @Override
    public Optional<ReadOnlyExpenseExpert> readExpenseExpert(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return expenseExpertStorage.readExpenseExpert(filePath);
    }

    @Override
    public void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert) throws IOException {
        saveExpenseExpert(expenseExpert, expenseExpertStorage.getExpenseExpertFilePath());
    }

    @Override
    public void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        expenseExpertStorage.saveExpenseExpert(expenseExpert, filePath);
    }

}
