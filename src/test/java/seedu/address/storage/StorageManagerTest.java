package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;
import static seedu.address.testutil.TypicalPersons.getTypicalModuleList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ContactList;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        //System.out.println(getTempFilePath("ab"));
        JsonContactListStorage contactListStorage = new JsonContactListStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonTaskListStorage taskListStorage = new JsonTaskListStorage(getTempFilePath("tl"));
        JsonModuleListStorage moduleListStorage = new JsonModuleListStorage(getTempFilePath("ms"));
        storageManager = new StorageManager(contactListStorage, userPrefsStorage, taskListStorage, moduleListStorage);
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
    public void contactListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonContactListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonContactListStorageTest} class.
         */
        ContactList original = getTypicalContactList();
        storageManager.saveContactList(original);
        ReadOnlyContactList retrieved = storageManager.readContactList().get();
        assertEquals(original, new ContactList(retrieved));
    }

    @Test
    public void taskListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonTaskListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonTaskListStorageTest} class.
         */
        PriorityTaskList original = getTypicalTaskList();
        storageManager.saveTaskList(original);
        ReadOnlyTaskList retrieved = storageManager.readTaskList().get();
        assertEquals(original, new PriorityTaskList(retrieved));
    }

    @Test
    public void moduleListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonModuleListStorage} class.
         */
        UniqueModuleList original = getTypicalModuleList();
        storageManager.saveModuleList(original);
        UniqueModuleList retrieved = storageManager.readModuleList().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void getContactListFilePath() {
        assertNotNull(storageManager.getContactListFilePath());
    }

    @Test
    public void getTaskListFilePath() {
        assertNotNull(storageManager.getTaskListFilePath());
    }

}
