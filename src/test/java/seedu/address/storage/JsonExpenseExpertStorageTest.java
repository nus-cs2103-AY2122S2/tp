package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.ANNUAL_NETFLIX_FEES;
import static seedu.address.testutil.TypicalExpenses.HOUSING_RENT;
import static seedu.address.testutil.TypicalExpenses.INTERNET_BILLS;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.ReadOnlyExpenseExpert;

public class JsonExpenseExpertStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExpenseExpertStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExpenseExpert_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExpenseExpert(null));
    }

    private java.util.Optional<ReadOnlyExpenseExpert> readExpenseExpert(String filePath) throws Exception {
        return new JsonExpenseExpertStorage(Paths.get(filePath))
                .readExpenseExpert(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExpenseExpert("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExpenseExpert("notJsonFormatExpenseExpert.json"));
    }

    @Test
    public void readExpenseExpert_invalidExpenseExpenseExpert_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseExpert(
                "invalidExpenseExpenseExpert.json"));
    }

    @Test
    public void readExpenseExpert_invalidAndValidExpenseExpenseExpert_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseExpert(
                "invalidAndValidExpenseExpenseExpert.json"));
    }

    @Test
    public void readAndSaveExpenseExpert_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempExpenseExpert.json");
        ExpenseExpert original = getTypicalExpenseExpert();
        JsonExpenseExpertStorage jsonExpenseExpertStorage = new JsonExpenseExpertStorage(filePath);

        // Save in new file and read back
        jsonExpenseExpertStorage.saveExpenseExpert(original, filePath);
        ReadOnlyExpenseExpert readBack = jsonExpenseExpertStorage.readExpenseExpert(filePath).get();
        assertEquals(original, new ExpenseExpert(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpense(HOUSING_RENT);
        original.removeExpense(ANNUAL_NETFLIX_FEES);
        jsonExpenseExpertStorage.saveExpenseExpert(original, filePath);
        readBack = jsonExpenseExpertStorage.readExpenseExpert(filePath).get();
        assertEquals(original, new ExpenseExpert(readBack));

        // Save and read without specifying file path
        original.addExpense(INTERNET_BILLS);
        jsonExpenseExpertStorage.saveExpenseExpert(original); // file path not specified
        readBack = jsonExpenseExpertStorage.readExpenseExpert().get(); // file path not specified
        assertEquals(original, new ExpenseExpert(readBack));

    }

    @Test
    public void saveExpenseExpert_nullExpenseExpert_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseExpert(null, "SomeFile.json"));
    }

    /**
     * Saves {@code expenseExpert} at the specified {@code filePath}.
     */
    private void saveExpenseExpert(ReadOnlyExpenseExpert expenseExpert, String filePath) {
        try {
            new JsonExpenseExpertStorage(Paths.get(filePath))
                    .saveExpenseExpert(expenseExpert, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExpenseExpert_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseExpert(new ExpenseExpert(), null));
    }
}
