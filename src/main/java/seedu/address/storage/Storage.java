package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseExpert;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;


/**
 * API of the Storage component
 */
public interface Storage extends ExpenseExpertStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getExpenseExpertFilePath();

    @Override
    Optional<ReadOnlyExpenseExpert> readExpenseExpert() throws DataConversionException, IOException;

    @Override
    void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert) throws IOException;

}
