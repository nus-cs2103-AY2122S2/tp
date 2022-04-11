package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.typical.TypicalLinkyTime;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonLinkyTimeStorage linkyTimeStorage = new JsonLinkyTimeStorage(getTempFilePath("app"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(linkyTimeStorage, userPrefsStorage);
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
    public void linkyTimeReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonLinkyTimeStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonLinkyTimeStorageTest} class.
         */
        LinkyTime original = TypicalLinkyTime.getTypicalLinkyTime();
        storageManager.saveLinkyTime(original);
        ReadOnlyLinkyTime retrieved = storageManager.readLinkyTime().get();
        assertEquals(original, new LinkyTime(retrieved));
    }

    @Test
    public void getLinkyTimeFilePath() {
        assertNotNull(storageManager.getLinkyTimeFilePath());
    }

}
