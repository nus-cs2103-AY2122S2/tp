package seedu.trackermon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.trackermon.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackermon.commons.core.GuiSettings;
import seedu.trackermon.commons.exceptions.DataConversionException;
import seedu.trackermon.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Storage) for {@code JsonUserPrefsStorage}.
 */
public class JsonUserPrefsStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserPrefsStorageTest");

    @TempDir
    public Path testFolder;

    /**
     * Tests that a {@code NullPointerException} is thrown when {@code readUserPrefs}
     * attempts to read a null file path.
     */
    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserPrefs(null));
    }

    private Optional<UserPrefs> readUserPrefs(String userPrefsFileInTestDataFolder) throws DataConversionException {
        Path prefsFilePath = addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
        return new JsonUserPrefsStorage(prefsFilePath).readUserPrefs(prefsFilePath);
    }

    /**
     * Tests that a {@code Optional<UserPrefs>} is not obtained after {@code readShowList} attempts to
     * read a non-existent file.
     */
    @Test
    public void readUserPrefs_missingFile_emptyResult() throws DataConversionException {
        assertFalse(readUserPrefs("NonExistentFile.json").isPresent());
    }

    /**
     * Tests that a {@code DataConversionException} is thrown when {@code readUserPrefs}
     * attempts to read a non-Json format data file.
     */
    @Test
    public void readUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserPrefs("NotJsonFormatUserPrefs.json"));
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }


    /**
     * Tests that {@code readUserPrefs} can successfully read a typical UserPref file.
     */
    @Test
    public void readUserPrefs_fileInOrder_successfullyRead() throws DataConversionException {
        UserPrefs expected = getTypicalUserPrefs();
        UserPrefs actual = readUserPrefs("TypicalUserPref.json").get();
        assertEquals(expected, actual);
    }

    /**
     * Tests that {@code readUserPrefs} can successfully read an empty UserPref file.
     */
    @Test
    public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        UserPrefs actual = readUserPrefs("EmptyUserPrefs.json").get();
        assertEquals(new UserPrefs(), actual);
    }

    /**
     * Tests that {@code readUserPrefs} can successfully read a UserPref file with extrra values.
     */
    @Test
    public void readUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        UserPrefs expected = getTypicalUserPrefs();
        UserPrefs actual = readUserPrefs("ExtraValuesUserPref.json").get();

        assertEquals(expected, actual);
    }

    private UserPrefs getTypicalUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
        userPrefs.setShowListFilePath(Paths.get("trackermon.json"));
        return userPrefs;
    }

    /**
     * Tests that a {@code NullPointerException} is thrown when {@code saveUserPrefs}
     * attempts to save a null {@code UserPref}.
     */
    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(null, "SomeFile.json"));
    }

    /**
     * Tests that a {@code NullPointerException} is thrown when {@code saveUserPrefs}
     * attempts to save into a null file path.
     */
    @Test
    public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(new UserPrefs(), null));
    }

    /**
     * Saves {@code userPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveUserPrefs(UserPrefs userPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonUserPrefsStorage(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                    .saveUserPrefs(userPrefs);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    /**
     * Tests that no errors are thrown when providing {@code saveUserPrefs} valid inputs.
     */
    @Test
    public void saveUserPrefs_allInOrder_success() throws DataConversionException, IOException {

        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        Path pefsFilePath = testFolder.resolve("TempPrefs.json");
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(pefsFilePath);

        //Try writing when the file doesn't exist
        jsonUserPrefsStorage.saveUserPrefs(original);
        UserPrefs readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
        jsonUserPrefsStorage.saveUserPrefs(original);
        readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);
    }

}
