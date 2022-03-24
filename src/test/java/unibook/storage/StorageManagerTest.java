package unibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import unibook.commons.core.GuiSettings;
import unibook.model.ReadOnlyUniBook;
import unibook.model.UniBook;
import unibook.model.UserPrefs;
import unibook.testutil.typicalclasses.TypicalUniBook;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUniBookStorage uniBookStorage = new JsonUniBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(uniBookStorage, userPrefsStorage);
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
    public void uniBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUniBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUniBookStorageTest} class.
         */
        UniBook original = TypicalUniBook.getTypicalUniBook();
        storageManager.saveUniBook(original);
        ReadOnlyUniBook retrieved = storageManager.readUniBook().get();
        assertEquals(original, new UniBook(retrieved));
    }

    @Test
    public void getUniBookFilePath() {
        assertNotNull(storageManager.getUniBookFilePath());
    }

}
