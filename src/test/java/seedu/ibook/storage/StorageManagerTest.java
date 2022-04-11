package seedu.ibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBook;
//import static seedu.ibook.testutil.TypicalPersons.getTypicalIBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.ibook.commons.core.GuiSettings;
//import seedu.ibook.model.IBook;
//import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.IBook;
import seedu.ibook.model.ReadOnlyIBook;
import seedu.ibook.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonIBookStorage iBookStorage = new JsonIBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(iBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonIBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonIBookStorageTest} class.
         */
        IBook original = getTypicalIBook();
        storageManager.saveIBook(original);
        ReadOnlyIBook retrieved = storageManager.readIBook().get();
        assertEquals(original, new IBook(retrieved));
    }

    @Test
    public void getIBookFilePath() {
        assertNotNull(storageManager.getIBookFilePath());
    }

}
