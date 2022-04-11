package seedu.unite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.unite.commons.core.GuiSettings;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.Unite;
import seedu.unite.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonUniteStorage uniteStorage = new JsonUniteStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(uniteStorage, userPrefsStorage);
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
    public void uniteReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUniteStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUniteStorageTest} class.
         */
        Unite original = getTypicalUnite();
        storageManager.saveUnite(original);
        ReadOnlyUnite retrieved = storageManager.readUnite().get();
        assertEquals(original, new Unite(retrieved));
    }

    @Test
    public void getUniteFilePath() {
        assertNotNull(storageManager.getUniteFilePath());
    }

}
