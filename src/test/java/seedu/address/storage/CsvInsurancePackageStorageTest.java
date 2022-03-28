package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInsurancePackages.GOLD;
import static seedu.address.testutil.TypicalInsurancePackages.SILVER;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.InsurancePackagesSet;

public class CsvInsurancePackageStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get(
            "src", "test", "data", "CsvInsurancePackageStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPackages_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readInsurancePackage(null));
    }

    private java.util.Optional<InsurancePackagesSet> readInsurancePackage(String filePath) throws Exception {
        return new CsvInsurancePackagesStorage(Paths.get(filePath))
                .readInsurancePackages(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readInsurancePackage("NonExistentFile.csv").isPresent());
    }

    @Test
    public void readPackages_invalidPackages_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readInsurancePackage("invalidPackages.csv"));
    }

    @Test
    public void readPackages_invalidAndValidPackages_noExceptionThrown() {
        try {
            readInsurancePackage("invalidAndValidPersonAddressBook.csv");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void readAndSaveInsurancePackages_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempInsurancePackages.csv");
        InsurancePackagesSet original = new InsurancePackagesSet();
        original.addPackage(SILVER);
        CsvInsurancePackagesStorage csvInsurancePackagesStorage = new CsvInsurancePackagesStorage(filePath);

        // Save in new file and read back
        csvInsurancePackagesStorage.saveInsurancePackages(original, filePath);
        InsurancePackagesSet readBack = csvInsurancePackagesStorage.readInsurancePackages(filePath).get();
        assertEquals(original, readBack);

        // Modify data, overwrite exiting file, and read back
        original.addPackage(GOLD);
        original.removePackage(SILVER);
        csvInsurancePackagesStorage.saveInsurancePackages(original, filePath);
        readBack = csvInsurancePackagesStorage.readInsurancePackages(filePath).get();
        assertEquals(original, readBack);

        // Save and read without specifying file path
        original.addPackage(SILVER);
        csvInsurancePackagesStorage.saveInsurancePackages(original); // file path not specified
        readBack = csvInsurancePackagesStorage.readInsurancePackages().get(); // file path not specified
        assertEquals(original, readBack);
    }

    /**
     * Saves {@code insurancePackages} at the specified {@code filePath}.
     */
    private void saveInsurancePackagesSet(InsurancePackagesSet insurancePackages, String filePath) {
        try {
            new CsvInsurancePackagesStorage(Paths.get(filePath))
                    .saveInsurancePackages(insurancePackages, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveInsurancePackages_nullInsurancePackagesSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInsurancePackagesSet(null, "SomeFile.csv"));
    }

    @Test
    public void saveInsurancePackages_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveInsurancePackagesSet(new InsurancePackagesSet(), null));
    }
}
