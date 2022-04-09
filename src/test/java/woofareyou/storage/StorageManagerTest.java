package woofareyou.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import woofareyou.commons.core.GuiSettings;
import woofareyou.model.PetBook;
import woofareyou.model.ReadOnlyPetBook;
import woofareyou.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonPetBookStorage petBookStorage = new JsonPetBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(petBookStorage, userPrefsStorage);
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
    public void petBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonPetBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonPetBookStorageTest} class.
         */
        PetBook original = getTypicalPetBook();
        storageManager.savePetBook(original);
        ReadOnlyPetBook retrieved = storageManager.readPetBook().get();
        assertEquals(original, new PetBook(retrieved));
    }

    @Test
    public void getPetBookFilePath() {
        assertNotNull(storageManager.getPetBookFilePath());
    }

}
