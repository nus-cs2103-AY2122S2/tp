package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseExpert;

/**
 * Represents a storage for {@link seedu.address.model.ExpenseExpert}.
 */
public interface ExpenseExpertStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExpenseExpertFilePath();

    /**
     * Returns ExpenseExpert data as a {@link ReadOnlyExpenseExpert}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyExpenseExpert> readExpenseExpert() throws DataConversionException, IOException;

    /**
     * @see #getExpenseExpertFilePath()
     */
    Optional<ReadOnlyExpenseExpert> readExpenseExpert(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExpenseExpert} to the storage.
     * @param expenseExpert cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert) throws IOException;

    /**
     * @see #saveExpenseExpert(ReadOnlyExpenseExpert) (ReadOnlyExpenseExpert)
     */
    void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert, Path filePath) throws IOException;

}
